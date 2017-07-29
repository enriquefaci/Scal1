package org.inanme

//@formatter:off
import cats._
import cats.instances.all._

import scala.language.higherKinds
//@formatter:on

object MyCats extends App {
  val len: String => Int = _.length
  println(Functor[List].map(List("scala", "cats"))(len))

  val x: Id[Int] = 2

}
