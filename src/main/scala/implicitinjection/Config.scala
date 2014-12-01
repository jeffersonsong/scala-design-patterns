package implicitinjection

trait Config {
  load
  val text: String
  def load: Unit
}

case class InMemoryConfig() extends Config {
  lazy val text = "Hello"
  def load = println("load: " + text)
}

case class MyApp(implicit config: Config) {
  def text = config.text
}

object Test extends App {
  implicit val config = InMemoryConfig()
  println("====================")
  val myApp = MyApp()
  println(myApp text)
  println("====================")
}