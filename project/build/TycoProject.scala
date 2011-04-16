import sbt._
import de.element34.sbteclipsify._

class TycoProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify {
  // val scalatest = "org.scalatest" % "scalatest" % "1.3"
  val glitter = "glitter" % "glitter" % "0.1" from "file:///home/julien/Workspace/glitter/target/scala_2.8.1/glitter-0.1.jar"
  override def artifactID = "tyco"
}