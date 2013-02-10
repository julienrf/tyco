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
import scalax.file.Path

/** A Site provides a set of resources reachable through URIs. */
trait Site {

  /** Map of path -> compiler, defining the reachable uri and how to render their content */
  private[this] val resources = new Resources

  type ResourceBuilder = {
    def ==> (c: Compiler): Resource
    def ==> (alias: String): Resource
  }

  implicit def makeBuilder(uri: String): ResourceBuilder = new {

    private def check(uri: String) {
      if (resources contains uri) {
        println("Warning: uri '" + uri + "' used multiple times")
      }
    }

    private def register(uri: String, compiler: Compiler): Resource = {
      val resource = Resource(uri, compiler)
      resources += resource
      resource
    }

    def ==> (compiler: Compiler): Resource = {
      check(uri)
      register(uri, compiler)
    }

    def ==> (alias: String): Resource = {
      check(uri)
      resources.compiler(alias) map (register(uri, _)) getOrElse sys.error("No resource defined for uri '" + alias + "'")
    }
  }

  /** Compiles all resources defined by this Site to a given target */
  def compile(target: Path = Path(".") / "target" / "www") {

    target.deleteRecursively()
    for ((uri, compiler) <- resources) {
      print("Compiling "+uri+" ")
      val resourcePath = if (uri.lastIndexOf("/") < uri.lastIndexOf(".")) {
        target / (uri, '/')
      } else {
        if (uri == "/") { // Special case of the root url
          target / "index.html"
        } else {
          target / (uri, '/') / "index.html"
        }
      }
      compiler.compile(resourcePath)
      println("OK");
    }
  }

  lazy val base = for {
    base <- Option(getClass.getResource("/"))
    path <- Path(base.toURI)
  } yield path
}
