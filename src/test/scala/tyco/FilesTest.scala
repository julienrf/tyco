package tyco

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import tyco.compiler.Files

class FilesTest extends FunSuite with ShouldMatchers {
  
  test("Files(\"src/test/scala/tyco/*.scala\") finds this test file") {
    val files = Files("src/test/scala/tyco/*.scala")
    assert(files.find(_.getName.equals("FilesTest.scala")).isDefined)
  }
}
