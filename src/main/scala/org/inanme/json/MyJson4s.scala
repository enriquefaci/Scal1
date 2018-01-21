package org.inanme.json

import org.json4s.JsonAST._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import scala.language.implicitConversions

object Part1 extends App {
  val r = render(("name", "mert") ~ ("numbers", Range(1, 10).toList)) \ "numbers" match {
    case JArray(x) ⇒
      println(x)
      x.collect { case JInt(n) ⇒ n + 10 }
    case _ ⇒ None
  }
  println(r)
  println(compact(List(1, 2, 3)))
  println(pretty(Seq(1, 2, 4)))

  def some1: Option[JValue] = Some("get" → "it")
  def none1: Option[JValue] = None

  val name: JField = "name" -> "mert"
  val surname: JField = "surname" -> "inan"
  val option1: JField = "some1" -> some1
  val option2: JField = "none1" -> none1
  //println(compact(name ~ surname ~ option1 ~ option2))
  println(name ~ surname ~ option2)
  println(name ~ surname)
  assert(name ~ surname ~ option2 == name ~ surname)

}
