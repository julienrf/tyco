package tyco

import tyco.compiler._
import scalax.file.Path

/**
 * Defines routes copying files from an assets directory.
 * JavaScript and CSS files are automatically minified.
 * Less files are automatically compiled in CSS.
 * CoffeeScript files are automatically compiled in JavaScript.
 */
trait Assets {
  self: Site =>

  /* Define routes for found assets */
  for {
    basePath <- base
    file <- (basePath / "assets").***
    if file.isFile
  } {
    def hasExtension(e: String) = file.extension.map(_ == e).getOrElse(false)
    val compiler =
      if (hasExtension("js")) {
        new TextFile(file) with JsMinifier
      } else if (hasExtension("coffee")) {
        new TextFile(file) with CoffeeScript with JsMinifier
      } else if (hasExtension("css")) {
        new TextFile(file) with CssMinifier
      } else if (hasExtension("less")) {
        new TextFile(file) with Less with CssMinifier
      } else {
        new BinFile(file)
      }

    "/" + file.relativize(basePath).path ==> compiler
  }

  // TODO provide an asset function returning the path of an existing asset or throwing an exception (or logging an error) if not found
}