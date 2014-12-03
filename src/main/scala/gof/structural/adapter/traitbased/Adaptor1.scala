package gof.structural.adapter.traitbased

class Adaptor1 extends Adaptee with Service {
  def invoke = printer
}

object Test1 extends App {
  val adaptor = new Adaptor1()
  val client = new Client(adaptor)
  client.doWork
}
