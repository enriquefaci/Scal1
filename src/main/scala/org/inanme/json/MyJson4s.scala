package org.inanme.json

import org.json4s.JInt
import org.json4s.JsonAST.{JArray, JField}
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import scala.language.implicitConversions

object MyJson4s

object Part1 extends App {

  val r = render(("name", "mert") ~ ("numbers", Range(1, 10).toList)) \ "numbers" match {
    case JArray(x) ⇒ x.collect { case x1: JInt ⇒ x1.num + 10 }
    case _ ⇒ None
  }

  println(r)

  println(compact(List(1, 2, 3)))
  println(pretty(Seq(1, 2, 4)))

  val name: JField = "name" -> "mert"
  val surname: JField = "surname" -> "inan"
  println(compact(name ~ surname))

}
