package tyco.util

import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

object urihelpers {
  
  trait UriFrom[A] extends (A => String)
  
  def uri[A](a: A)(implicit uriMaker: UriFrom[A]) = uriMaker(a)
  
  class StringW(s: String) {
    
    def slugify = {
      def matches(c: Char, pattern: String): Boolean = pattern.r.findFirstIn(c.toString).isDefined
      s.toLowerCase.map { _ match {
          case a if matches(a, "[áàâä]") => 'a'
          case e if matches(e, "[éèêë]") => 'e'
          case i if matches(i, "[íìîï]") => 'i'
          case o if matches(o, "[óòôö]") => 'o'
          case u if matches(u, "[úùûü]") => 'u'
          case 'ç' => 'c'
          case nonWord if matches(nonWord, "\\W") => '-'
          case c => c
        }
      }.replaceAll("-{2,}", "-").replaceAll("-+$", "").replaceAll("^-+", "")
    }
  }
  
  class DateW(d: Date) {
    def format(pattern: String) = new SimpleDateFormat(pattern).format(d)
    def format(pattern: String, lang: String) = new SimpleDateFormat(pattern, new Locale(lang)).format(d)
  }
  
  implicit def String2RichW(s: String) = new StringW(s)
  implicit def Date2DateW(d: Date) = new DateW(d)
}