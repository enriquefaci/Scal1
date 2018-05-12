name := "Scal1"
version := "2.0.0"
scalaVersion := "2.12.3"
cancelable in Global := true

crossScalaVersions := Seq("2.11.9", "2.12.4")

import versions._

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test",
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "com.beachape" %% "enumeratum" % enumeratumVersion,

  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,

  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-laws" % catsVersion,
  "org.typelevel" %% "cats-free" % catsVersion,
  "org.typelevel" %% "cats-effect" % "0.10.1",

  "io.monix" %% "monix" % "2.3.3",
  "io.monix" %% "monix-cats" % "2.3.3",

  "co.fs2" %% "fs2-core" % "0.10.4",
  "co.fs2" %% "fs2-io" % "0.10.4",

  "com.storm-enroute" %% "scalameter" % "0.9",

  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.zalando" %% "grafter" % "2.1.1",

  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",

  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.json4s" %% "json4s-scalaz" % json4sVersion,
  "de.heikoseeberger" %% "akka-http-json4s" % "1.17.0",

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
  //A -X option suggests permanence, while a -Y could disappear at any time
  "-encoding", "UTF-8", // source files are in UTF-8
  "-explaintypes", // Explain type errors in more detail.
  "-deprecation", // warn about use of deprecated APIs
  "-unchecked", // warn about unchecked type parameters
  "-feature", // warn about misused language features
  "-language:postfixOps", // allow higher kinded types without `import scala.language.postfixOps`
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
  "-language:reflectiveCalls",
  "-Xlint", // enable handy linter warnings
  //"-Xfatal-warnings", // turn compiler warnings into errors
  "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
)

initialCommands in console := importScalaz
