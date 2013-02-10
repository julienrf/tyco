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

case class Item(uri: String, name: String)

object Item {
  def apply(t: Tuple2[String, String]): Item = Item(t._1, t._2)
  implicit def tuple2ToItem(tuple: Tuple2[String, String]) = Item(tuple._1, tuple._2)
}

/** The sitemap reflects the way the web pages are structured. Currently only a flat sitemap is supported */
case class Sitemap(items: Traversable[Item]) {
  /** Build a Navigation object having as default item the item with `current` as uri */
  def navigation(active: Item) = Navigation(items, active)
}

/** Navigation data contain the list of items of a site and the current item */
case class Navigation(items: Traversable[Item], active: Item)

object Navigation {
  /** Display a `Navigation` instance as a HTML list */
  def _template(navigation: Navigation): Xml =
      'nav (for (it <- navigation.items) yield {
        val a: EmptyTag = if (it == navigation.active) 'a % ('class->"active") else 'a // FIXME not pretty
        a %('href->it.uri, 'title->it.name) :: it.name
      })
}
