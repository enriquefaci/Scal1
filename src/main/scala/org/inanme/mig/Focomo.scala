package org.inanme.mig

/** A for comprehension ready Monad */
case class Focomo[A](value: A) {
  self =>

  /** satisfies monadic binding of a function f that returns B */
  def map[B](f: A => B): Focomo[B] = {
    println(s"map! $value")
    Focomo(f(value))
  }

  /** satisfies monadic binding of a function f that returns Focomo[B] */
  def flatMap[B](f: A => Focomo[B]): Focomo[B] = {
    println(s"flatMap! $value")
    f(value)
  }

  /** expect this to be called in a `Unit` for comprehension */
  def foreach[U](f: A => U): Unit = {
    println(s"foreach! $value")
    f(value)
  }

  /**
    * for comprehension's `if` statements trigger this.
    * In a more useful monad, the result of the application
    * of a value to function f would determine the a special subclass
    * of the monad or other either-or behavior.
    */
  def filter(f: A => Boolean): Focomo[A] = {
    println(s"filter! $value")
    this
  }

  /** provides a delegate handler for calls to #withFilter */
  class WithFilter(p: A => Boolean) {
    println("with filter!")

    def map[B](f: A => B): Focomo[B] = self.filter(p).map(f)

    def flatMap[B](f: A => Focomo[B]): Focomo[B] = self.filter(p).flatMap(f)

    def foreach[U](f: A => U): Unit = self.filter(p).foreach(f)

    def withFilter(q: A => Boolean): WithFilter =
      new WithFilter(x => p(x) && q(x))
  }

  /** called with conditional statement in for comprehension */
  def withFilter(p: A => Boolean): WithFilter = new WithFilter(p)
}

object FocomoMain extends App {
  for {
    i <- Focomo(2)
    j <- Focomo(3)
    k <- Focomo(4)
  } yield i + j + k

}
