import scalaz._

val addStuff: Reader[Int, Int] = for {
  a <- Reader((_: Int) * 2)
  b <- Reader((_: Int) + 10)
} yield a + b

val c: Int = addStuff(3)

//do not touch this perfect