import scala.util._
import scalaz.Maybe.Just
import scalaz._

val result = for {
  x ← Try(1)
  y ← Try(2)
} yield x + y

\/.fromTryCatchNonFatal(1 / 2).toMaybe

Just(10).map(_ + 1)

Maybe.empty[Int].map(_ + 1)

class GenerationException(message: String) extends Exception(message, null, false, true)

implicit val geNotNothing = NotNothing.isNotNothing[GenerationException]

def queryNextNumber(): GenerationException \/ Long = \/.fromTryCatchThrowable {
  val source = Math.round(Math.random * 100)
  if (source <= 60) source
  else throw new GenerationException(s"The generated number $source is too big!")
}

queryNextNumber()