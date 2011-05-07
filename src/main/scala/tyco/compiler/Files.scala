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

import java.io.{File, FilenameFilter}
import org.apache.commons.io.filefilter.WildcardFileFilter

import collection.JavaConversions._

/** Helper to find files */
object Files {
  def apply(path: String): Traversable[File] = {
    val dir = new File(if (path.startsWith(fileSep)) fileSep else ".")
    val dirs = if (dir != null) List(dir) else Nil
    walk(dirs, path.split(fileSep).toList)
  }
  
  private def walk(dirs: List[File], path: List[String]): Traversable[File] = path match {
    case dir :: remainingPath => {
      val filter: FilenameFilter = new WildcardFileFilter(path.head)
      val filesFound = for {
        dir <- dirs
        file <- dir.listFiles(filter)
        if file != null
      } yield file
      walk(filesFound, remainingPath)
    }
    case Nil => dirs
  }
  
  private val fileSep = File.separator
}
