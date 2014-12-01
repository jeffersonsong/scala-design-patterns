package adapter.traitbased

trait Adaptor2 extends Service {
  self : Adaptee =>
  def invoke = printer
}

object Test2 extends App {
  val adaptor = new Adaptee() with Adaptor2
  val client = new Client(adaptor)
  client.doWork
}
