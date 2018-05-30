case class Reader[A, B](run: A => B) {
  def apply(x: A): B = run(x)

  def map[C](f: B => C): Reader[A, C] = Reader(run andThen f)

  def flatMap[C](f: B => Reader[A, C]): Reader[A, C] = {
    val k0: Reader[A, Reader[A, C]] = map(f)
    val k1: A => Reader[A, C] = (a: A) => map(f)(a)
    val k2: A => C = (a: A) => map(f)(a)(a)
    Reader((a: A) => map(f)(a)(a))
  }

}
val inc: Int => Int = _ + 1
val double: Int => Int = _ * 2
val incR: Reader[Int, Int] = Reader(inc)
val doubleR: Reader[Int, Int] = Reader(double)
val identityR: Reader[Int, Int] = Reader(identity)
val add: Int => Reader[Int, Int] = (x: Int) => Reader((y: Int) => {
  println(s"x : $x, y : $y")
  x + y
})

val map1 = (incR map double) (3)
val flatMap1 = (incR flatMap add) (4)
val flatMap2 = incR.flatMap(x => identityR.map(y => {
  println(s"x : $x, y : $y")
  x + y
}))(4)
val flatMap3 = incR.flatMap(x => doubleR.map(y => {
  println(s"x : $x, y : $y")
  x + y
}))(4)
