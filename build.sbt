name := "Scal1"

version := "2.0.0"

scalaVersion := "2.12.2"

val scalazVersion = "7.2.13"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.typelevel" %% "cats" % "0.9.0",
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test"
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

scalacOptions ++= Seq(
  // See other posts in the series for other helpful options
  "-feature",
  "-language:higherKinds"
)

initialCommands in console := importScalaz
