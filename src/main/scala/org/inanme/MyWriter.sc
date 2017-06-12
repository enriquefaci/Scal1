import scalaz._
import Scalaz._

def add(a: Int, b: Int): Writer[String, Int] = {
  (a + b).set(s"Adding $a and $b")
}

def multiply(a: Int, b: Int): Writer[String, Int] = {
  (a * b).set(s"Multiplying $a and $b")
}

val result = for {
  x <- add(3, 4)
  y <- multiply(x, 6)
} yield y
// res0: Writer[String,Int] = Writer((Adding 3 and 4Multiplying 7 and 6,42))

result.written

result.value
