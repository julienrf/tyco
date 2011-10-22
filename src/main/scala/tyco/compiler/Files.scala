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
import com.esotericsoftware.wildcard.Paths

import collection.JavaConversions._

// FIXME Find files from src/resources path by default
/**
 * Helper to find files
 * It uses the [[http://code.google.com/p/wildcard/ wildcard]] library under the hood and share the same syntax for wildcards.
 * Usage example:
 * {{{
 *   for (images <- Files("src/resources/assets/**/*.jpg")) {
 *     println(images.getName)
 *   }
 * }}}
 */
object Files {
  def apply(path: String): Traversable[File] =
    new Paths(if (path.startsWith(fileSep)) fileSep else ".", path).getFiles()
  
  private val fileSep = File.separator
}
