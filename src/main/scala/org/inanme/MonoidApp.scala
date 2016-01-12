package org.inanme

object MonoidApp extends App {

  trait Monoid[A] {
    def unit: A

    def compose(a1: A, a2: A): A

  }

  implicit object StringMonoid extends Monoid[String] {
    override def unit: String = ""

    override def compose(a1: String, a2: String): String = a1 + a2
  }

  implicit object IntMonoid extends Monoid[Int] {
    override def unit: Int = 0

    override def compose(a1: Int, a2: Int): Int = a1 + a2
  }

  implicit object ListMonoid extends Monoid[List[Int]] {
    override def unit: List[Int] = List()

    override def compose(a1: List[Int], a2: List[Int]): List[Int] = a1 ++ a2
  }

  //http://stackoverflow.com/questions/4465948/what-are-scala-context-and-view-bounds
  //def f[A : B](a: A) = g(a) // where g requires an implicit value of type B[A]
  implicit class Sum[A: Monoid](list: List[A]) {
    def sum1: A = list.foldLeft(implicitly[Monoid[A]].unit)(implicitly[Monoid[A]].compose)
  }

  def sum2[A: Monoid](list: List[A]) =
    list.foldLeft(implicitly[Monoid[A]].unit)(implicitly[Monoid[A]].compose)


  val stringList: List[String] = Range(0, 10).map("str" + _).toList
  val intList: List[Int] = Range(0, 10).toList
  val listList: List[List[Int]] = Range(0, 10).map(Range(0, _).toList).toList

  println(intList.sum1)
  println(listList.sum1)

  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
  }
  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B) = xs.foldLeft(b)(f)
    }
  }

  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.unit, m.compose)
  }
  println(sum(stringList))


}
