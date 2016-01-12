name := "Scal1"

version := "2.0.0"

scalaVersion := "2.11.7"

val scalazVersion = "7.1.6"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.4",
  "io.spray" %% "spray-can" % "1.3.1",
  "io.spray" %% "spray-routing" % "1.3.1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test",
  "joda-time" % "joda-time" % "2.7",
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test"
)

scalacOptions += "-feature"

initialCommands in console := "import scalaz._, Scalaz._"
