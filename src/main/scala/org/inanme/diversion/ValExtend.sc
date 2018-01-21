//https://docs.scala-lang.org/tutorials/FAQ/initialization-order.html

//Naturally when a val is overridden, it is not initialized more than once.
trait A {
  val x1: String
  val x2: String = "mom"
  println("A: " + x1 + ", " + x2)
  def lazyPrint(): Unit = println("A: " + x1 + ", " + x2)
}
class B extends A {
  override val x1: String = "hello"
  println("B: " + x1 + ", " + x2)
}
class C extends B {
  override val x2: String = "dad"
  println("C: " + x1 + ", " + x2)
}
val c = new C()
c.lazyPrint()