package org.inanme.scheck

import org.scalacheck._
import org.scalacheck.Prop._
import Result._

object CombiningGenScalaCheckProperties extends Properties("Combining Properties") {
  val stringsOnly = Prop.forAll(Gen.alphaStr) {
    x: String => (x != "") ==> x.size >= 0
  }
  val positiveNumbersOnly = Prop.forAll(Gen.posNum[Int]) {
    x: Int => x >= 0
  }
  val positiveNumbers2Only = Prop.forAll(Gen.posNum[Int]) {
    x: Int => x > 0
  }
  val alwaysPass = Prop.forAll {
    x: Int => true
  }
  val wontPass = Prop.forAll((x: Int, y: Int) => x + y > 0)
  property("And") = stringsOnly && positiveNumbersOnly
  property("Or") = stringsOnly || wontPass
}

object BiggerSpecification extends Properties("A bigger test specification") {
  property("testList") = forAll { (l1: List[Int], l2: List[Int]) =>
    l1.size + l2.size == (l1 ::: l2).size
  }
  property("Check concatenated string") = forAll { (a: String, b: String) =>
    a.concat(b) == a + b
  }
}
object MyScalaCheck extends App {
  case class Rectangle(width: Double, height: Double) {
    // note that there’s a bug in the function below!
    val area = if (width % 11 == 0) width * 1.0001 * height else width * height
    // correct version of the area method
    val areaCorrect = width * height

    def biggerThan(r: Rectangle) = area > r.area
  }

  import org.scalacheck.Gen

  val rectangleGen: Gen[(Rectangle, Double, Double)] = for {

    height <- Gen.choose(0, 9999)
    width <- Gen.choose(0, 9999)
  } yield (Rectangle(width, height), width, height)
  object RectangleSpecification extends Properties("Rectangle specification") {
    property("Test area") = forAll(rectangleGen) { (input: (Rectangle, Double, Double)) =>
      val (r, width, height) = input
      r.area == width * height
    }
  }
  RectangleSpecification.check()
}
