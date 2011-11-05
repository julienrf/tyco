package tyco.data.blog

import tyco.data.user.User
import java.util.Date

case class Post(title: String, teaser: String, content: String, author: User, date: Date = new Date(), comments: Iterable[Comment] = Nil)

case class Blog(name: String, posts: Iterable[Post])

case class Comment(content: String, post: Post, author: User, date: Date = new Date())

object Post {
  /**
   * Order post chronologically (newest first)
   */
  implicit def postOrdering(implicit dateOrder: Ordering[Date]) = new Ordering[Post] {
    override def compare(p1: Post, p2: Post) = -dateOrder.compare(p1.date, p2.date)
  }
}