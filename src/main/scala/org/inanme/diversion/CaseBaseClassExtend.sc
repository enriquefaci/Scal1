//case class Person(val name: String)
class Person(val name: String)
case class Student(id: Int) extends Person(name = "mert")
val m = Student(id = 10)
m.name

m match {
  case Student(id) ⇒ println(id)
  case _ ⇒ println("none")
}
