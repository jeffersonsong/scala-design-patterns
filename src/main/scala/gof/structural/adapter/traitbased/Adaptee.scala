package gof.structural.adapter.traitbased

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
