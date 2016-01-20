name := "Scal1"

version := "2.0.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.typelevel" %% "cats" % "0.9.0",
  "org.scalaz" %% "scalaz-core" % "7.2.10"
)

val importScalaz =
  """
    |import scalaz._;
    |import Scalaz._;""".stripMargin

val importCats =
  """
    |import cats._;
    |import cats.data._;
    |import cats.implicits._
    |import cats.instances._;
    |import cats.instances.all._;
    |import cats.syntax.functor._;""".stripMargin

initialCommands in console := importScalaz
