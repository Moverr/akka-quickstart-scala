
name := "akka-quickstart-scala"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.3"



libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.13",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.6.13" % "Test",
  "org.scalatest" %% "scalatest" % "3.1.0" % "Test"
)
