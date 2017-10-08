
import org.zalando.grafter.Rewriter._
import org.zalando.grafter.syntax.query._
import org.zalando.grafter.syntax.visualize._

case class B(b: String)

case class C(c: String)

case class A(b: B, c: C)

val b = B("b")
val c = C("c")
val a = A(b, c).singleton[B]

a.asDotString

a
