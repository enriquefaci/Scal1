package org.inanme.diversion

import scala.language.postfixOps
import scala.annotation.tailrec

object MyFac extends App {

  implicit final class Fac(val n: Int) extends AnyVal {
    def fac(n: Int): BigInt = {
      @tailrec
      def _fac(n: Int, result: BigInt): BigInt =
        if (n == 1) result else _fac(n - 1, result * n)

      _fac(n, 1)
    }

    def ! : BigInt = fac(n)
  }

  println(10 !)
}
