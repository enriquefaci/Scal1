package org.inanme.apple

import simulacrum._

object Appfdsakfdjskla extends App {


  @typeclass trait Semigroup[A] {
    @op("|+|") def append(x: A, y: A): A
  }

  implicit val semigroupInt: Semigroup[Int] = (x: Int, y: Int) => x + y

  import Semigroup.ops._

  println(1 |+| 2)

}
