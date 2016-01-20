type F = Int => Int
val inc: F = _ + 1
val double: F = _ * 2

def lift(f: F): Int => List[Int] = x => List(f(x))

val xs = Range(0, 10).toList

case class Reader[A, B](run: A => B) {
  def apply(x: A): B = run(x)

  def map[C](f: B => C): Reader[A, C]
  = Reader(run andThen f)

  def flatMap[C](f: B => Reader[A, C]): Reader[A, C]
  = Reader(x => map(f)(x)(x))
}

val r1 = Reader(inc)
val f3 = (x: Int) => Reader((y: Int) => "" + x + y)

(r1 map double) (3)
(r1 flatMap f3) (3)

r1 map f3
(r1 map f3) (3)
(r1 map f3) (3)(3)