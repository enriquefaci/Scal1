package org.inanme

import java.util.UUID

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scalaz.Scalaz._
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

  def myName(step: String): Reader[String, String] = Reader {
    step + ", I am " + _
  }

  def localExample: Reader[String, (String, String, String)] = for {
    a <- myName("First")
    b <- myName("Second") >=> Reader {
      _ + "dy"
    }
    c <- myName("Third")
  } yield (a, b, c)


}

object Sea extends App {

  def getX: Future[Option[Int]] = Future(Some(1))

  def getY: Future[Option[Int]] = Future(Some(2))

  val x_y: OptionT[Future, Int] = for {
    x <- OptionT(getX)
    y <- OptionT(getY)
  } yield x + y

  val out = x_y.run
  out onComplete {
    case Success(x) => println(x)
    case Failure(th) => th.printStackTrace()
  }
  Await.ready(out, Duration.Inf)
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
