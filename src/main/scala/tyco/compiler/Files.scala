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
  
  private def walk(dirs: List[File], path: List[String]): Traversable[File] = {
    path match {
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
  }
  
  private val fileSep = File.separator
}
