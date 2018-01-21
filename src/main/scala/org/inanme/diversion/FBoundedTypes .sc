import scala.util.Try
//An F-bounded type is parameterized over its own subtypes

trait Pet[A <: Pet[A]] {
  def name: String
  def renamed(newName: String): A // note this return type
}
case class Fish(name: String, age: Int) extends Pet[Fish] { // note the type argument
  def renamed(newName: String) = copy(name = newName)
}
