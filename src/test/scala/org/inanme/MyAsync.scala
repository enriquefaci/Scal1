package org.inanme

import org.scalatest.AsyncFlatSpec

import scala.concurrent._
import scalaz.Scalaz._

class MyAsync extends AsyncFlatSpec {

  behavior of "Async adder"

  it should "add values" in {
    val f1 = Future({
      Thread.sleep(10000L)
      1
    })
    val f2 = Future(2)

    ^(f1, f2)(_ + _) map (it => assert(it == 3))
  }

}
