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

case class Resource(compiler: Compiler, uri: String) {
  import java.io.{PrintWriter, File}
  
  def compile(base: String) {
    print("Compiling "+uri+" … ")
    val out = new PrintWriter(new File(base+uri+"/index.html"))
    out.write(compiler.process)
    out.close();
    println("OK");
  }
}

abstract class Compiler {
  def process: String
  def ==> (uri: String) = Resource(this, uri)
}

import glitter._

class Glitter(content: Xml) extends Compiler {
  override def process = content.render
}

class TextFile(file: String) extends Compiler {
  override def process = io.Source.fromFile(file).mkString
}

trait CoffeeScript extends Compiler {
  abstract override def process = {
    super.process
  }
}

trait JsMinifier extends Compiler {
  abstract override def process = {
    super.process
  }
}

trait Markdown extends Compiler {
  abstract override def process = {
    super.process
  }
}

trait Sass extends Compiler {
  abstract override def process = {
    super.process
  }
}

trait CssMinifier extends Compiler {
  abstract override def process = {
    super.process
  }
}
