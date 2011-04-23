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

import tyco.compiler.Resource

/** A Site provides a set of resources reachable through URIs. */
trait Site {
  
  def resources: Traversable[Resource]
  
  /** Compiles all resources defined by this Site to a given target */
  def compile(target: String = "www") {
    resources foreach (_.compile(target))
  }
}