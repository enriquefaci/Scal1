import scalaz._
import Scalaz._

def toInts(maybeInts: List[String]): ValidationNel[Throwable, List[Int]] = {
  val validationList = maybeInts map { s =>
    Validation.fromTryCatchNonFatal(s.toInt :: Nil).toValidationNel
  }
  validationList reduce (_ +++ _)
}

toInts(List("1", "2", "3"))
toInts(List("1", "2", "3", "x", "M"))

val yes = 3.14.successNel[String]
val doh = "Error".failureNel[Double]

// or shorthand
(yes |@| yes){_ + _} // Success(6.28)
(yes |@| doh){_ + _} // Failure(NonEmptyList(Error))
(doh |@| yes){_ + _} // Failure(NonEmptyList(Error))
(doh |@| doh){_ + _} // Failure(NonEmptyList(Error, Error))