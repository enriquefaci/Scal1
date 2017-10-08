import cats._
import cats.instances.all._
import cats.syntax.all._

val func1 = (x: Int) => x.toDouble
val func2 = (y: Double) => y * 2
val func3 = func1.map(func2)
func3(3)


1.show
"mert".show
1.some.show
1.some === 1.some
1.some |+| 2.some
"mert" |+| " " |+| "inan"
val map1 = Map("a" -> 1, "b" -> 2)
val map2 = Map("b" -> 3, "d" -> 4)
map1 |+| map2
(1 to 10).reduce(implicitly[Semigroup[Int]].combine)
