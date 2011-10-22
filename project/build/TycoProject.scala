import sbt._
import de.element34.sbteclipsify._

class TycoProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify {
  
  val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.6.1"
  val glitter = "glitter" % "glitter" % "0.1" from "file:///home/julien/Workspace/glitter/target/scala_2.9.0/glitter-0.1.jar"
  val commonsIo = "commons-io" % "commons-io" % "2.0.1"
  
  override def artifactID = "tyco"
}