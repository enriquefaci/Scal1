import scala.util._

implicit object String2Exception extends (String => Throwable) {
  override def apply(v1: String): Throwable = new RuntimeException(v1)
}
val r: Throwable Either Int = Right(3)
val l: Throwable Either Int = Left("tuck")
r.toTry
l.toTry
