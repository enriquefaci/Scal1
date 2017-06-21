package org.inanme

import scala.annotation.tailrec

object Sun extends App {

  implicit class Xman(s: String) {
    def tolow: String = s.toLowerCase
  }

  val xman = "MERT"

  println(xman.tolow)


  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @tailrec
    def _isSorted(as: Array[A], runningResult: Boolean): Boolean = {
      as match {
        case Array(a, b, rest@_*) => _isSorted(as.slice(1, as.length), ordered(a, b) && runningResult)
        case Array(_) => runningResult
        case Array() => runningResult
      }
    }

    _isSorted(as, true)
  }

}
