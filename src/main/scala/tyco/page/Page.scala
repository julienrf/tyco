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

/** Generic page concept */
package tyco.page

import glitter._
import tyco.navigation.{Item, Navigation}

/** A Page is a web content identified by a slug, having a name and a content */
case class Page(name: String, slug: String, content: Xml) extends Item {
  def uri = "/" + slug
}

object Page {
  /** Basic page display (in a `section`, displaying its name in a `h1` tag) */
  def _template(page: Page) =
      'section %('id->"container") :: 'div %('class->"content") :: (
          'h1 :: page.name
        | page.content
      )
}

/** A set of pages. */
trait Pages {
  
  def pages: Traversable[Page]
  
  /** Compute a `Navigation` instance according to a given current page */
  def navigation(current: Page) = Navigation(pages, current)
}
