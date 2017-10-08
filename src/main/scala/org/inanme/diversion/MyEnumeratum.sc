import enumeratum._

sealed trait Greeting extends EnumEntry
object Greeting extends Enum[Greeting] {
  val values = findValues
  case object Hello extends Greeting
  case object GoodBye extends Greeting
  case object Hi extends Greeting
  case object Bye extends Greeting
}
sealed abstract class Level(override val entryName: String) extends EnumEntry
object Level extends Enum[Level] {
  val values = findValues
  case object Level_1 extends Level("1")
  case object Level_2 extends Level("2")
}
Level.withName("1")