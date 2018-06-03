case class Pair1[T](first: T, second: T) {
  def smaller(implicit ev: T => Ordered[T]) = {
    if (first < second) first else second
  }
}
Pair1(1, 2).smaller
case class Pair2[T](first: T, second: T) {

  import scala.math.Ordering.Implicits._

  def smaller(implicit ev: Ordering[T]) = {
    if (first < second) first else second
  }
}
Pair2(1, 2).smaller
case class Pair3[T](first: T, second: T) {
  def smaller(implicit ev: Ordering[T]) = {
    import ev._
    if (first < second) first else second
  }
}
Pair3(1, 2).smaller
case class Pair4[T: Ordering](first: T, second: T) {
  def smaller = {
    import Ordered._
    if (first < second) first else second
  }
}
Pair4(1, 2).smaller


def firstLast[A, C](it: C)(implicit ev: C <:< Iterable[A]) =
  (it.head, it.last)
