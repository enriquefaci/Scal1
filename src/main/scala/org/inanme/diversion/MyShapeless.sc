// http://jto.github.io/articles/getting-started-with-shapeless/

import shapeless._
import shapeless.syntax.std.tuple._

case class User(name: String)

val demo = 42 :: "Hello" :: User("Julien") :: HNil

// select finds the first element of a given type in a HList
// Note that scalac will correctly infer the type of s to be String.
val s1 = demo.select[String] // returns "Hello".

//demo.select[List[Int]] // Compilation error. demo does not contain a List[Int]

// Again i is correctly inferred as Int
val i1: Int = demo.head // returns 42.

// You can also us pattern matching on HList
val i :: s :: u :: HNil = demo


(1, "foo", 12.3).tail
