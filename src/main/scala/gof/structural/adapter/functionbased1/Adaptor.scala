package gof.structural.adapter.functionbased1

class Adaptee {
  def printer: Unit = {println("Adaptee Hello")}
}

trait Service {
  def invoke: Unit
}

class Client(service: Service) {
  def doWork = {
    service.invoke
  }
}

object Adaptor extends Service {
  val adaptee = new Adaptee()
  val invoke = adaptee.printer
}

object Test extends App {
  val client = new Client(Adaptor)
  client doWork
}
