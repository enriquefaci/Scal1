//https://espinhogr.github.io/scala/2015/07/12/trampolines-in-scala.html

import scala.util.control.TailCalls._

def inc1(list: List[Int]): List[Int] = {
  list match {
    case Nil ⇒ Nil
    case e :: rest ⇒ e + 1 :: inc1(rest)
  }
}

def inc2(list: List[Int]): TailRec[List[Int]] = {
  list match {
    case Nil ⇒ done(Nil)
    case e :: rest ⇒ tailcall(inc2(rest).map(e + 1 :: _))
  }
}

inc2(1 to 10 toList).result