package org.inanme

import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object MyFuture extends App {

  trait Currency

  case object USD extends Currency

  case object CHF extends Currency

  def isProfitable(curr1: Int, curr2: Int): Boolean = Random.nextBoolean()

  object Connection {

    def getCurrentValue(currency: Currency): Int = {
      val x = Random.nextInt(5).toLong
      TimeUnit.SECONDS.sleep(x)
      if (x == 3l) {
        throw new IllegalArgumentException("failed")
      }
      currency match {
        case USD => 1
        case CHF => 2
        case _ => -1
      }
    }

    def buy(amount: Int, currency: Currency) = ()
  }

  def simulate() = {
    val usdQuote: Future[Int] = Future {
      Connection.getCurrentValue(USD)
    }

    usdQuote.onComplete {
      case Success(x) => println(x)
      case Failure(_) => println("Failed to fetch USD")
    }

    val chfQuote = Future {
      Connection.getCurrentValue(CHF)
    }

    chfQuote.onComplete {
      case Success(x) => println(x)
      case Failure(_) => println("Failed to fetch CHF")
    }

    for {
      usd <- usdQuote
      chf <- chfQuote
    } {
      println(isProfitable(usd, chf))
    }
  }

}
