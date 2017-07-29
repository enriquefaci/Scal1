import scalaz.Scalaz._
import scalaz._

def sequenceList[F[_] : Applicative, A](xs: List[F[A]]): F[List[A]] =
  xs.foldRight(List.empty[A].point[F])((a, b) => {
    println(s"a and b $a, $b")
    ^(a, b)(_ :: _)
  })

sequenceList(List(some(1), some(2)))
sequenceList(List(some(1), none))
//sequenceList(List(\/.right(42), \/.left(NonEmptyList("oops"))))

List.empty[Int].point[Option]
List.empty[String]

List(some(1), some(2), some(30)).
  //  reduce(sum)
  reduce((a, b) => ^(a, b)(_ |+| _))

def sum(a: Option[Int], b: Option[Int]) = for {
  v1 <- a
  v2 <- b
} yield v1 + v2

//List(some(1), some(2), some(30)).foreach(println)

^(1.some, 2.some)(_ |+| _)
^(List(1.some), List(2.some))(_ |+| _)
^(List("test".some), List("1test".some))(_ |+| _)
^(some(List(1)), some(List(2)))(_ |+| _)
^(some(List(1)), some(List(2)))(_ :: _)
^(some(2), some(List(2)))(_ :: _)
