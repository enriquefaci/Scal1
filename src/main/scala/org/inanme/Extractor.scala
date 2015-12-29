package org.inanme

trait User {
  def name: String
}

class FreeUser(override val name: String) extends User

class PremiumUser(override val name: String) extends User

class IntUser(override val name: String) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}

object PremiumUser {
  def unapply(user: PremiumUser): Option[String] = Some(user.name)
}

object IntUser {
  def unapply(user: IntUser): Option[Int] = Some(1)
}


object Extractor extends App {
  val user: User = new IntUser("Daniel")
  val output = user match {
    case FreeUser(name) => "Hello " + name
    case PremiumUser(name) => "Welcome back, dear " + name
    case IntUser(name) => 1 / name
  }
  println(output)
}
