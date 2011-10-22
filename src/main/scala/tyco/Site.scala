/* Tyco  Copyright (C) 2011  Julien Richard-Foy <julien@richard-foy.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tyco

import tyco.compiler.Compiler
import org.apache.commons.io.FileUtils
import java.io.File

/** A Site provides a set of resources reachable through URIs. */
trait Site {
  
  /** Map of path -> compiler, defining the reachable uri and how to render their content */
  private[this] val resources = collection.mutable.Map.empty[String, Compiler]
  
  type ResourceBuilder = {
    def ==> (c: Compiler)
    def ==> (alias: String)
  }
  
  implicit def makeBuilder(uri: String): ResourceBuilder = new {
  
    private def check(uri: String) {
      if (resources contains uri) {
        println("Warning: uri '" + uri + "' used multiple times")
      }
    }
    
    def ==> (compiler: Compiler) {
      check(uri)
      resources += uri -> compiler
    }
    
    def ==> (alias: String) {
      check(uri)
      resources.get(alias) match {
        case Some(compiler) => resources += uri -> compiler
        case None => println("Warning: no resource defined for uri '" + alias + "'")
      }
    }
  }
  
  /** Compiles all resources defined by this Site to a given target */
  def compile(target: String = "target/www") {
    FileUtils.deleteDirectory(new File(target))
    for ((uri, compiler) <- resources) {
      print("Compiling "+uri+" ")
      val path = target + (if (uri.lastIndexOf("/") < uri.lastIndexOf(".")) uri
                           else uri + "/index.html")
      compiler.compile(path)
      println("OK");
    }
  }
}
