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

package tyco.compiler

/** A web site contains resources reachable through URIs.
  * This type defines how such URI are mapped with content provided by a `Compiler`
  */
case class Resource(compiler: Compiler, uri: String) {
  import java.io.{PrintWriter, File}
  
  /** Compile a resource */
  def compile(target: String) {
    print("Compiling "+uri+" ")
    val out = new PrintWriter(new File(target+uri+"/index.html"))
    out.write(compiler.process)
    out.close();
    println("OK");
  }
}

/** Base class of compilers, subclassed to implement CoffeeScript or SCSS compilers.
  * Implement your own compiler as a trait if it is intended to be chained
  */
abstract class Compiler {
  /** Produce an output. */
  def process: String
  def ==> (uri: String) = Resource(this, uri)
}

import glitter._

/** Glitter compiler, just rendering the xml */
class Glitter(content: Xml) extends Compiler {
  override def process = content.render
}

/** TextFile compiler, reading a file */
class TextFile(file: String) extends Compiler {
  override def process = io.Source.fromFile(file).mkString
}

/** CoffeeScript compiler, translating a coffee script to legacy javascript.
  * Note that this compiler is an abstract trait, it is intended to be chained
  * with another compiler, e.g. `new TextFile(file) with CoffeeScript` */
trait CoffeeScript extends Compiler {
  abstract override def process = {
    super.process
  }
}

/** Minify a given javascript content.
  * Chain this compiler like this: `new TextFile(file) with JsMinifier`
  */
trait JsMinifier extends Compiler {
  abstract override def process = {
    super.process
  }
}

/** Compile Markdown markup to HTML */
trait Markdown extends Compiler {
  abstract override def process = {
    super.process
  }
}

/** Compile SCSS into legacy CSS */
trait Scss extends Compiler {
  abstract override def process = {
    super.process
  }
}

/** Minify a CSS input */
trait CssMinifier extends Compiler {
  abstract override def process = {
    super.process
  }
}
