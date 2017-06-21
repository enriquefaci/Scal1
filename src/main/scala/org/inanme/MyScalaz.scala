package org.inanme

import java.util.UUID

import scalaz._

object MyScalaz extends App {

  case class User(id: Int,
                  username: String,
                  email: String = "test@test.com",
                  firstName: String = "firstname",
                  lastName: String = "lastname",
                  supervisorId: Int = 1)

  trait UserRepository {
    def get(id: Int): User

    def find(username: String): User
  }

  object UserRepository extends UserRepository {
    override def get(id: Int): User = User(id, UUID.randomUUID().toString)

    override def find(username: String): User = User(1, username)
  }

  trait Users {
    def getUser(id: Int): Reader[UserRepository, User] = Reader(_.get(id))

    def findUser(username: String): Reader[UserRepository, User] = Reader(_.find(username))
  }

  object Users extends Users {
    def userEmail(id: Int): Reader[UserRepository, String] = {
      getUser(id) map (_.email)
    }

    def userInfo(username: String): Reader[UserRepository, Map[String, String]] =
      for {
        user <- findUser(username)
        boss <- getUser(user.supervisorId)
      } yield Map(
        "fullName" -> s"${user.firstName} ${user.lastName}",
        "email" -> s"${user.email}",
        "boss" -> s"${boss.firstName} ${boss.lastName}"
      )
  }

  println(Users.userInfo("mert")(UserRepository))
}

object Earth extends App {
  def age(name: String): String \/ Int = name match {
    case "Erik" => \/-(30)
    case _ => -\/(s"unknown age of $name")
  }

  val totalAge = for {
    a <- age("Erik")
    b <- age("John")
    c <- age("Jane")
  } yield a + b + c

  println(totalAge)

}
