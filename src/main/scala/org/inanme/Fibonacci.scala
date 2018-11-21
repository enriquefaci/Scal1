package org.inanme

import scala.annotation.tailrec
import scala.util.control.TailCalls._


object Fibonacci extends App {

  def f1(n: Int): Int = {
    n match {
      case 0 => 0
      case 1 => 1
      case _ => f1(n - 1) + f1(n - 2)
    }
  }

  def f2(n: Int): TailRec[Int] = {
    n match {
      case 0 => done(0)
      case 1 => done(1)
      case _ => tailcall {
        for {
          n1 <- f2(n - 1)
          n2 <- f2(n - 2)
        } yield n1 + n2
      }
    }
  }

  /**
    * 0 1 1
    * 1 1 2
    * 2 3 5
    * 5 8 13
    * 13 21 34
    *
    * @param n
    * @return
    */
  def f3(n: Int): Int = {
    @tailrec
    def inner(counter: Int, prev: Int, current: Int, next: Int): Int = {
      if (counter <= 1) {
        current
      } else {
        inner(counter - 1, current, next, current + next)
      }
    }

    inner(n, 0, 1, 1)
  }


  println(f1(19))
  println(f2(19).result)
  println(f3(19))

}
