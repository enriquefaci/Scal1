package org.inanme

import java.util.concurrent.{CountDownLatch, TimeUnit}

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success, Try}

object MyPromise extends App {

  println("ok")

  val count = new CountDownLatch(1)
  val random = new Random(System.currentTimeMillis())

  def startDoingSomething(name: String): Int = {
    val rnd = random.nextInt(5) + 1
    val fail = random.nextBoolean()
    if (fail && "producer".equals(name)) {
      throw new IllegalArgumentException(rnd.toString)
    }
    println(name + " will wait " + rnd)
    TimeUnit.SECONDS.sleep(rnd)
    rnd
  }

  val p = Promise[Int]()
  val f = p.future
  val producer = Future {
    p complete Try {
      startDoingSomething("Producer")
    }
    println("Done producer")
  }

  val consumer = Future[Unit] {
    startDoingSomething("consumer")
    f.onComplete {
      case Success(x) => println(x)
      case Failure(th) => th.printStackTrace()
    }
    count.countDown()
  }

  count.await()
  consumer.foreach(println)

}
