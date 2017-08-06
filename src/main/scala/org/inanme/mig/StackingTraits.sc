sealed trait Service {
  def operation: Unit
}

trait Audit extends Service {
  abstract override def operation: Unit = {
    println("Audit")
    super.operation
  }
}

trait Logger extends Service {
  abstract override def operation: Unit = {
    println("Logger")
    super.operation
  }
}

class Service1Impl extends Service {
  override def operation: Unit = {
    println("Impl")
  }
}

val s = new Service1Impl with Logger with Audit
s.operation
