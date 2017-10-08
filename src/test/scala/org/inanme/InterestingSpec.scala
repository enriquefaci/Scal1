package org.inanme

import org.scalacheck.Gen
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class InterestingSpec extends FlatSpec with Matchers with Eventually with ScalaFutures with BeforeAndAfter {

  it should "have size 0" in {

    val result = List(1) match {
      case f :: s :: rest => List(f, s)
      case _ => Nil
    }
    result should be(Nil)
    result shouldBe Nil

    "xman" should startWith("x")

    println(Gen.oneOf(Seq(1, 2, 3, 4)))
    List(1) should have size 1
    List(1) should contain(1)
  }

  it should "fds" in {
    val map = Map(
      "Jimmy Page" -> "Led Zeppelin",
      "Sting" -> "The Police",
      "Aimee Mann" -> "Til\' Tuesday")
    map should contain key ("Sting")
    map should contain value ("Led Zeppelin")
    map should not contain key("Brian May")
  }

  before {
    println("before")
  }

  after {
    println("after")
  }


}
