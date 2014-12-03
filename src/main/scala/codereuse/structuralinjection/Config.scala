package codereuse.structuralinjection

trait Config {
  load
  val text: String
  def load: Unit
}

case class InMemoryConfig() extends Config {
  lazy val text = "Hello"
  def load = println("load: " + text)
}

trait Context

case class Session(context: {val config: Config}) {
  def text = context.config.text
}

object MyContext extends Context {
  lazy val config = InMemoryConfig()
}

object Test extends App {
  val session = Session(MyContext)
  println(session.text)
}
