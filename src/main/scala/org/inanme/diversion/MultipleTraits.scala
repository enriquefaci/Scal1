package org.inanme.diversion

object MultipleTraits extends App {

  object MyMath {
    trait Inc[T] {
      def inc(t: T): T
    }
    trait Twice[T] {
      def twice(t: T): T
    }
    object Implicits {
      implicit object IncInt extends Inc[Int] {
        def inc(i: Int) = i + 1
      }
      implicit object TwiceInt extends Twice[Int] {
        def twice(t: Int): Int = t + t
      }
    }
  }

  import MyMath.Implicits._
  import MyMath._

  def process[T: Inc : Twice](s: Seq[T]): Seq[T] = {
    val mapper: T ⇒ T = implicitly[Inc[T]].inc _ andThen implicitly[Twice[T]].twice _
    s.map(mapper)
  }

  println(process(Vector(1, 2, 3, 4)))
  assert(process(Vector(1, 2, 3, 4)) == Vector(4, 6, 8, 10))

}
