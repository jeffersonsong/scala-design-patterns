package singleton

object Session {
  val id = "session"
  override def toString = "session singleton"
}

object Test extends App {
  val id = Session.id
  println(id)
  println(Session)
}
