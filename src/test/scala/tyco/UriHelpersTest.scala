package tyco

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import java.text.SimpleDateFormat
import util.urihelpers._

class UriHelpersTest extends FunSuite with ShouldMatchers {

  test ("Date.format") {
    val date = new SimpleDateFormat("yyyy/M/d").parse("1985/12/25");
    date.format("yyyy/MM/dd") should be ("1985/12/25")
  }
  
  test ("String.slugify") {
    "NRSTWÇauieç’k.kné345".slugify should be ("nrstwcauiec-k-kne345")
    "éèça (foo)".slugify should be ("eeca-foo")
    "« bar »".slugify should be ("bar")
  }
}