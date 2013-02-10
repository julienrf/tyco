name := "tyco"

version := "0.1-SNAPSHOT"

organization := "com.github.julienrf"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.0",
  "com.github.julienrf" %% "glitter" % "0.2"
)

resolvers += "Typesafe releases" at "http://repo.typesafe.com/typesafe/releases/"