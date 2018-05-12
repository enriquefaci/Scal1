

import scala.reflect.runtime.universe._

val list1 = 1 to 10 toList

val list2 = 1 to 10 map (_.toString) toList

f1(list1)
f1(list2)

def f1[A: TypeTag](c: List[A]) = c match {
  case _ if typeOf[A] <:< typeOf[String] ⇒ "string"
  case _ if typeOf[A] <:< typeOf[Int] ⇒ "int"
}


class C1
class C2 extends C1
typeOf[C2] <:< typeOf[C1]


def handle(a: Any): Unit = a match {
  case vs: List[String] => println("strings: " + vs.map(_.length).sum)
  case vs: List[Int]    => println("ints: " + vs.sum)
  case _ =>
}

handle(List("hello", "world")) // output: "strings: 10"
handle(List(1, 2, 3))          // ClassCastException... oh no!
