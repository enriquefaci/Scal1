package org.inanme.fs2tw

import cats.effect.{Effect, IO}
import fs2._
import fs2.async.mutable.Queue
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object SampleCode extends App {
  val infiniteStream = Stream.emit(1).repeat.covary[IO].map(_ + 3).take(10)
  val output = infiniteStream.compile.toVector.unsafeRunSync()
  val m = Vector(1, 2, 3)
  println(s"Result >> $output") // prints Result >> Vector(4, 4, 4, 4, ... )

}
