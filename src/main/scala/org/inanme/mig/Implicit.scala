package org.inanme.mig

import java.util._
import java.util.Calendar._

object ImplicitEx extends App {

  implicit class RangeMaker(val left: Int) extends AnyVal {
    def -->(right: Int) = left to right
  }
  //implicit def int2RangeMaker(left: Int): RangeMaker = new RangeMaker(left)

  implicit val k1 = 100
  //implicit val k2 = 101
  def imp1(implicit m: Int) = m

  val oneTo10 = 1 --> 10
  println(implicitly[Int])
  println(imp1)

  implicit class Fluent(val interval: Int) extends AnyVal {
    def days = this
    def ago: Date = {
      val now = Calendar.getInstance
      now.add(DAY_OF_MONTH, -interval)
      now.getTime()
    }
  }
  println(2.days.ago)

  implicit class MyRange(val num: Int) extends AnyVal {
    def times(block: => Any): Unit = {
      for (i <- 1 to num) {
        block
      }
    }
  }

  2 times {
    println("hello")
  }


}