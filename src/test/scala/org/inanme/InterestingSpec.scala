package org.inanme

import org.scalatest.FlatSpec

class InterestingSpec extends FlatSpec {

  behavior of "list"

  it should "have size 0" in {

    val result = List(1) match {
      case f :: s :: rest => List(f, s)
      case _ => Nil
    }
    assert(result == Nil)

    hello()
  }


}
