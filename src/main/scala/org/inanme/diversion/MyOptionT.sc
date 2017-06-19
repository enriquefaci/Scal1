import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaz.Scalaz._
import scalaz._

val f1 = Future[Option[Int]] {1.some}
//val f2 = Future[Option[Int]] {2.some}
val f2 = Future[Option[Int]] {throw new IllegalArgumentException("x")}
val result = for {
  i1 <- OptionT(f1)
  i2 <- OptionT(f2)
} yield i1 + i2
result.run map {
  case Some(x) => println("succ " + x)
  case None => println("fail ")
}
result.run onComplete {
  case scala.util.Success(Some(x)) => println("succ " + x)
  case scala.util.Success(None) => println("none " )
  case scala.util.Failure(x: Throwable) => println("fail " + x.getMessage)
}
