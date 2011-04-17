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

/** Generic navigation concept */
package tyco.navigation

import glitter._

/** A resource is anything having an URI. */
trait Resource {
  def uri: String
}

/** An navigation item is a resource associated with a (human readable) name. */
trait Item extends Resource {
  def name: String
}

/** Navigation data contain the list of items of a site and the current item */
case class Navigation(items: Traversable[Item], current: Item)

object Navigation {
  /** Display a `Navigation` instance as a HTML list */
  def _template(navigation: Navigation): Xml =
      'nav :: forM (navigation.items) { it =>
        val a: EmptyTag = if (it == navigation.current) 'a % ('class->"active") else 'a // FIXME not pretty
        a %('href->it.uri, 'title->it.name) :: it.name
      }
}
