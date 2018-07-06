package org.inanme.diversion

import java.lang.{Boolean => JBoolean}
import java.util._
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger
import java.util.function._
import akka.actor.ActorSystem
import akka.pattern.{CircuitBreaker, CircuitBreakerOpenException}
import akka.stream.ActorMaterializer
import scala.util._
import scala.concurrent._
import scala.concurrent.duration._

object MyCircuitBreaker extends App {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val breaker =
    new CircuitBreaker(
      system.scheduler,
      maxFailures = 1,
      callTimeout = 10 seconds,
      resetTimeout = 5 seconds).onOpen(notifyMeOnOpen()).onClose(notifyMeOnClosed())

  def notifyMeOnOpen(): Unit = println("My CircuitBreaker is now open")

  def notifyMeOnClosed(): Unit = println("My CircuitBreaker is now closed")

  val counter = new AtomicInteger(1)
  val successBody: Callable[Future[Int]] = () => Future(1)
  val failureBody: Callable[Future[Int]] =
    () => {
      println(s"failureBody ${counter.get()}")
      if (counter.getAndIncrement() % 2 == 0) Future(counter.get()) else throw new RuntimeException("msg")
    }
  val failureFn: BiFunction[Optional[Int], Optional[Throwable], JBoolean] =
    (_, f) => f.filter(th => th.isInstanceOf[RuntimeException]).isPresent
  val successFn: BiFunction[Optional[Int], Optional[Throwable], JBoolean] =
    (s, _) => s.filter(_ == 1).isPresent
  Range(1, 10).foreach { _ =>
    breaker.callWithCircuitBreaker(failureBody, failureFn)
      .recover {
        case _: CircuitBreakerOpenException => -1
        case _: RuntimeException => -2
      }
      .onComplete {
        case Success(x) => println(x)
        case _ => print("failure1")
      }
    Thread.sleep(1000)
  }

}
