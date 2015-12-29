package org.inanme

object Sun extends App {

  implicit class Xman(s: String) {
    def tol: String = s.toLowerCase
  }

  val xman = "MERT"

  println(xman.tol)

}
