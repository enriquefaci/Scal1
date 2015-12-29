package org.inanme

object F2x extends App {

  def createErrorMessage: Int => String = {
    case 1 => "network"
    case 2 => "I/O"
    case _ => "Unknown"
  }

  def xman(x: Int, y: Int): String = String.valueOf(x + y)

  def process(validate: (Int, Int) => String): Unit = {
    println("Now doubling " + validate(100, 200))
  }

  val x: (Int, Int) => String = {
    case (100, 200) => "301"
    case (x, y) => String.valueOf(x + y)
  }
  // The literal syntax for a partial function is one or more case match clauses enclosed in curly braces.
  process {
    case (100, 200) => "301"
    case (x, y) => String.valueOf(x + y)
  }

  process(xman)

  println(createErrorMessage(1))
  println(createErrorMessage(2))
  println(createErrorMessage(3))


  def m1(ints: Int*): Int = ints.sum

  println(m1(List(1, 2, 3, 4): _*))
}