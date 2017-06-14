import scalaz._
import Scalaz._

val f = Kleisli { (x: Int) => (x + 1).some }
val g = Kleisli { (x: Int) => (x * 100).some }

4.some >>= (f <=< g)
4.some >>= (f >=> g)


def myName(step: String): Reader[String, String] = Reader {step + ", I am " + _}
def localExample: Reader[String, (String, String, String)] = for {
  a <- myName("First")
  b <- myName("Second") >=> Reader { _ + "dy"}
  c <- myName("Third")
} yield (a, b, c)
localExample("Fred")
