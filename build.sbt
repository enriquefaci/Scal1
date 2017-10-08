name := "Scal1"
version := "2.0.0"
scalaVersion := "2.12.3"
cancelable in Global := true

crossScalaVersions := Seq("2.11.9", "2.12.3")

import versions._

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,

  //"org.typelevel" %% "cats-core" % "0.9.0",
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "org.typelevel" %% "cats-laws" % "1.0.0-MF",
  "org.typelevel" %% "cats-effect" % "0.4",
  "co.fs2" %% "fs2-core" % "0.10.0-M6",
  "co.fs2" %% "fs2-io" % "0.10.0-M6",

  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.zalando" %% "grafter" % "2.1.1",

  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",

  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.json4s" %% "json4s-scalaz" % json4sVersion,

  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",

  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.h2database" % "h2" % "1.4.185",
  "ch.qos.logback" % "logback-classic" % "1.1.2",

  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion

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
  "-feature", "-deprecation", "-explaintypes", "-unchecked", "-encoding", "utf8",
  //"-Xcheckinit",
  "-language:higherKinds",
  "-Ypartial-unification"
)

initialCommands in console := importScalaz
