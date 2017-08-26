import scalaz._
import Scalaz._

def toInts(maybeInts: List[String]): ValidationNel[Throwable, List[Int]] = {
  val validationList = maybeInts map { s =>
    Validation.fromTryCatchNonFatal(s.toInt :: Nil).toValidationNel
  }
  validationList reduce (_ +++ _)
}

toInts(List("1", "2", "3")) //scalaz.ValidationNel[Throwable,List[Int]]
toInts(List("1", "2", "3", "x", "M"))

val yes: ValidationNel[String, Double] = 3.14.successNel[String]
val doh: ValidationNel[String, Double] = "Error".failureNel[Double]

val a = 42.success[String]
val b = "Boo".failure[Int]
val c ="Boo1".failure[Int]
(a |@| b |@| c ) (_ + _ + _)
a +++ b +++ c

// or shorthand
(yes |@| yes) (_ + _) // Validation[scalaz.NonEmptyList[String],Double] = Success(6.28)
(yes |@| doh) (_ + _) // Failure(NonEmptyList(Error))
(doh |@| yes) (_ + _) // Failure(NonEmptyList(Error))
(doh |@| doh) (_ + _) // Failure(NonEmptyList(Error, Error))

case class PersonValidationError(field: String, message: String)
case class Person(name: String, age: Int)

def validatePerson(person: Person): ValidationNel[PersonValidationError, Person] = {
  val ageMin: ValidationNel[PersonValidationError, Person] =
    if (person.age < 18) PersonValidationError("age", "age limit 18").failureNel[Person]
    else person.successNel
  val ageMax: ValidationNel[PersonValidationError, Person] =
    if (person.age > 25) PersonValidationError("age", "age limit 18").failureNel[Person]
    else person.successNel
  val name: ValidationNel[PersonValidationError, Person] =
    if (person.name.length < 4) PersonValidationError("name", "name limit 4").failureNel[Person]
    else person.successNel
  (ageMin |@| ageMax |@| name) ((_, _, p) => p)
}

implicit object PersonSemigroup extends Semigroup[Person] {
  override def append(f1: Person, f2: ⇒ Person) = f1
}

def validatePerson2(person: Person): ValidationNel[PersonValidationError, Person] = {
  val ageMin: ValidationNel[PersonValidationError, Person] =
    if (person.age < 18) PersonValidationError("age", "age limit 18").failureNel[Person]
    else person.successNel
  val ageMax: ValidationNel[PersonValidationError, Person] =
    if (person.age > 25) PersonValidationError("age", "age limit 18").failureNel[Person]
    else person.successNel
  val name: ValidationNel[PersonValidationError, Person] =
    if (person.name.length < 4) PersonValidationError("name", "name limit 4").failureNel[Person]
    else person.successNel

  ageMin +++ ageMax +++ name
}

validatePerson2(Person("jo", 2))
validatePerson2(Person("jojojo", 22))

val v1: Validation[Throwable, Int] = 1.success[Throwable]
val v2: Validation[Throwable, Int] = (new RuntimeException).failure[Int]
val v3: Validation[Throwable, Int] = Validation.fromTryCatchNonFatal("c".toInt)

val result: ValidationNel[Throwable, List[Int]] =
  List(v1, v1, v1).map(_.toValidationNel).sequenceU

List(v1, v1, v1).map(_.toValidationNel).sequenceU.rightMap(_.head)
