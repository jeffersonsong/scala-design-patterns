package codereuse.cakepattern

trait Context

trait Config {
  load
  val text: String
  def load: Unit
}

case class InMemoryConfig() extends Config {
  lazy val text = "Hello"
  def load = println("load: " + text)
}

trait MyContext extends Context {
  this: Config =>
  def welcome = this.text
}

object Env extends InMemoryConfig with MyContext

object Test extends App {
  println(Env.text)
}