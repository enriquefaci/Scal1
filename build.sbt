name := "Scal1"

version := "2.0.0"

scalaVersion := "2.12.3"

val scalazVersion = "7.2.14"
val json4sVersion = "3.5.3"
val monocleVersion = "1.4.0"


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "org.typelevel" %% "cats" % "0.9.0",

  "com.chuusai" %% "shapeless" % "2.3.2",

  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",

  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.json4s" %% "json4s-scalaz" % json4sVersion,

  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",

  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.h2database" % "h2" % "1.4.185",
  "ch.qos.logback" % "logback-classic" % "1.1.2"

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
