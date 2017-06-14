import scalaz._
import Scalaz._


val addStuff: Reader[Int, Int] = for {
  a <- Reader { (_: Int) * 2 }
  b <- Reader { (_: Int) + 10 }
} yield a + b

addStuff(3)

//do not touch this perfect