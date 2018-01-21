import org.scalacheck._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalacheck.Test.Parameters
import scala.concurrent.duration._

//////////////////////////
val s = Vector(1, 2, 3, 4)
val m1Gen = Gen.oneOf(s)
val m2Gen = Gen.oneOf(s)

val m: Prop = forAll(m1Gen) { m1 ⇒
  forAll(m2Gen) { m2 ⇒
    true
  }
}
m.check
////////////
forAll((list: List[Int]) ⇒ list.nonEmpty).check
val nonEmptyList: Gen[List[Int]] = arbitrary[List[Int]].suchThat(_.nonEmpty)
forAll(nonEmptyList)(_.nonEmpty).check
forAll((s: String, s2: String) ⇒ (s + s2).startsWith(s)).check
//////////////////////////
val m1_100 = Gen.choose(1, 100)
forAll(m1_100)(it ⇒ it >= 0 && it <= 100).check
//////////////////////////
sealed trait Truth
object True extends Truth
object False extends Truth
val truthGen = Gen.oneOf(Seq(False, True))
//////////////////////////
forAll { n: Int =>
  (n >= 0 && n < 10000) ==> (List.fill(n)("").length == n)
}.check
//////////////////////////
forAll { n: Int ⇒
  //n > Integer.MIN_VALUE ==>
  Math.abs(n) >= 0
}.check
//////////////////////////
def myMagicFunction(n: Int, m: Int) = n + m
forAll { (m: Int, n: Int) =>
  (m > 0 && n > 0) ==> {
    val res = myMagicFunction(n, m)
    (res >= m) :| "result > #1" &&
      (res >= n) :| "result > #2" &&
      (res == m + n) :| "result not sum"
  }
}.check
//////////////////////////
case class Person(name: String, age: Int)
val personGenerator: Gen[Person] = for {
  name <- arbitrary[String].suchThat(!_.isEmpty)
  age <- Gen.choose(0, 100)
} yield Person(name, age)
implicit val personArbitrary: Arbitrary[Person] = Arbitrary(personGenerator)
forAll((p: Person) ⇒ p.name.length > 0).check
forAll((p: (Person, Int)) ⇒ p._1.name.length > 0).check
//////////////////////////
forAll(Gen.const(3))(_ =? 3).check(Parameters.default.withMinSize(5))

val genDuration: Gen[FiniteDuration] = Gen.choose(200, 1000).map(_ millis)
val nonEmptyStrGen: Gen[String] = arbitrary[String].suchThat(!_.isEmpty)