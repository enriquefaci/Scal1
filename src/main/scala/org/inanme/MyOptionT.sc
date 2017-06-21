import scala.concurrent._
import scala.util.{Failure, Success}
import scalaz._
import Scalaz._

implicit val currThreadExecutor =
  ExecutionContext.fromExecutor((command: Runnable) => command.run())

def getX: Future[Option[Int]] = Future(Some(1))

def getY: Future[Option[Int]] = Future(Some(2))

val x_y: OptionT[Future, Int] = for {
  x <- OptionT(getX)
  y <- OptionT(getY)
} yield x + y

val out:Future[Option[Int]] = x_y.run

out onComplete {
  case Success(Some(x)) => println(x)
  case Success(None) => println("None")
  case Failure(th) => th.printStackTrace()
}
