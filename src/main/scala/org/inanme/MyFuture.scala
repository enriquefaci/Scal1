package org.inanme

import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Random, Success}

object MyFuture extends App {
  def blockMe(op: String): Int = {
    val x = Random.nextInt(5)
    TimeUnit.SECONDS.sleep(x)
    if (x == 3l) {
      throw new IllegalArgumentException("failed " + op)
    }
    println(op + " finished")
    x
  }

  val op1: Future[Int] = Future {
    blockMe("op1")
  }

  val op2: Future[Int] = Future {
    blockMe("op2")
  }

  op1.onComplete {
    case Success(x) => println(x)
    case Failure(_) => println("Failed to fetch op1")
  }

  op2.onComplete {
    case Success(x) => println(x)
    case Failure(_) => println("Failed to fetch op2")
  }

  val result: Future[Int] = for {
    v1 <- op1
    v2 <- op2
  } yield v1 + v2

  Await.ready(result, Duration.Inf)
  result map println

}
