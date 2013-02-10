package tyco

import compiler.Compiler

case class Resource(uri: String, compiler: Compiler)

class Resources {
  private val resources = collection.mutable.ListBuffer.empty[Resource]
  
  def +=(resource: Resource) = resources += resource
  
  def contains(uri: String): Boolean = find(uri).isDefined
  
  def compiler(uri: String): Option[Compiler] = find(uri) map (_.compiler)
  
  def find(uri: String) = resources find (_.uri == uri)
}

object Resources {
  implicit def toTraversable(resources: Resources): Traversable[(String, Compiler)] = resources.resources map (r => (r.uri, r.compiler))
}