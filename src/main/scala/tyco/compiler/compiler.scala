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

import java.io.File
import org.apache.commons.io.FileUtils
import glitter._

/** Base class of compilers, subclassed to implement CoffeeScript or SCSS compilers.
  * Implement your own compiler as a trait if it is intended to be chained
  */
abstract class Compiler {
  
  type Elem
  
  /** Produce an output. */
  def process: Traversable[Elem]
  def compile(target: String)
}

abstract class TextCompiler extends Compiler {
  
  override type Elem = Char
  
  override def compile(target: String) {
    FileUtils.writeStringToFile(new File(target), process.mkString)
  }
}

abstract class BinCompiler extends Compiler {
  
  override type Elem = Byte
  
  override def compile(target: String) {
    FileUtils.writeByteArrayToFile(new File(target), process.toArray)
  }
}

/** Glitter compiler, just rendering the xml */
class Glitter(content: Xml) extends TextCompiler {
  override def process: Traversable[Char] = content.render
}

/** TextFile compiler, reading a file */
class TextFile(file: File) extends TextCompiler {
  override def process: Traversable[Char] = io.Source.fromFile(file).mkString
}

/** CoffeeScript compiler, translating a coffee script to legacy javascript.
  * Note that this compiler is an abstract trait, it is intended to be chained
  * with another compiler, e.g. `new TextFile(file) with CoffeeScript` */
trait CoffeeScript extends TextCompiler {
  abstract override def process = {
    super.process
  }
}

/** Minify a given javascript content.
  * Chain this compiler like this: `new TextFile(file) with JsMinifier`
  */
trait JsMinifier extends TextCompiler {
  abstract override def process = {
    super.process
  }
}

/** Compile Markdown markup to HTML */
trait Markdown extends TextCompiler {
  abstract override def process = {
    super.process
  }
}

/** Compile SCSS into legacy CSS */
trait Scss extends TextCompiler {
  abstract override def process = {
    super.process
  }
}

/** Minify a CSS input */
trait CssMinifier extends TextCompiler {
  abstract override def process = {
    super.process
  }
}

class BinFile(file: File) extends BinCompiler {
  override def process: Traversable[Byte] = FileUtils.readFileToByteArray(file)
}
