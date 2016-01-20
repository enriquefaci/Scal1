package org.inanme

import cats._
import cats.instances.all._

object MyCats extends App {
  val len: String => Int = _.length
  println(Functor[List].map(List("scala", "cats"))(len))

}
