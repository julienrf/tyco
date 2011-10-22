package tyco.data.blog

import tyco.data.user.User
import java.util.Date

case class Post(title: String, teaser: String, content: String, author: User, date: Date = new Date(), comments: Iterable[Comment] = Nil)

case class Blog(name: String, posts: Iterable[Post])

case class Comment(content: String, post: Post, author: User, date: Date = new Date())