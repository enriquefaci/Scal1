//https://functionaltechramblings.wordpress.com/2016/02/28/monads-and-scalaz-what-when-why-and-how/

import scalaz._
import Scalaz._

case class Generator(seed: Int)

object Generator {
  def generateRandomInt(): State[Generator, Int] = {
    for {
      g <- get[Generator]             // Get the current state
      _ <- put(Generator(g.seed + 1)) // Update the state with the new generator
    } yield g.seed + 5                // return the "randomly" generated number
  }
}
import Generator._

val computation = for {
  x <- generateRandomInt()
  y <- generateRandomInt()
} yield (x, y)

val (newGenerator, (xOutput, yOutput)) = computation.run(Generator(57))
