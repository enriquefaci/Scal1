package org.inanme.diversion

import scala.language.implicitConversions

object MyImplicitConversions {
  class A(val n: Int)
  class B(val m: Int, val n: Int)
  class C(val m: Int, val n: Int, val o: Int) {
    def total = m + n + o
  }
}

import org.inanme.diversion.MyImplicitConversions._

// This demonstrates implicit conversion chaining restrictions
object T1 extends App { // to make it easy to test on REPL
  implicit def toA(n: Int): A = new A(n)
  implicit def aToB(a: A): B = new B(a.n, a.n)
  implicit def bToC(b: B): C = new C(b.m, b.n, b.m + b.n)
  // won't work
  //println(5.total)
  //println(new A(5).total)

  // works
  println(new B(5, 5).total)
  println(new C(5, 5, 10).total)
}

object T2 extends App {
  implicit def toA(n: Int): A = new A(n)
  implicit def aToB[A1](a: A1)(implicit f: A1 => A): B = new B(a.n, a.n)
  implicit def bToC[B1](b: B1)(implicit f: B1 => B): C = new C(b.m, b.n, b.m + b.n)

  // works
  println(5.total)
  println(new A(5).total)
  println(new B(5, 5).total)
  println(new C(5, 5, 10).total)
}

