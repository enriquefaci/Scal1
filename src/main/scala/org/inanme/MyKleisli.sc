import scalaz._
import Scalaz._

val f = Kleisli { (x: Int) => (x + 1).some }
val g = Kleisli { (x: Int) => (x * 100).some }

4.some >>= (f <=< g)
4.some >>= (f >=> g)
