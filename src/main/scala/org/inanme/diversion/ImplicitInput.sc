

case class Boxy(x: Int)
def someContextCreator(x: Int)(block: Boxy ⇒ Unit): Unit = {
  block(Boxy(x))
}

def someFunc(m: String)(implicit t: Boxy) = println(m.length + t.x)

someContextCreator(1) { implicit boxy: Boxy ⇒
  someFunc("mert")
}
