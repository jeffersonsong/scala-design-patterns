package gof.structural.adapter.functionbased3

class Adaptee {
  def printer(title: String, name: String) = println(title + " " + name)
}

trait Service {
  val print: String => Unit
}

class Client(service: Service) {
  def write(s: String) = service.print(s)
}

object Adaptor extends Adaptee with Service {
  val print = printer("Dr", _: String)
}

object Test extends App {
  val c = new Client(Adaptor)
  c.write("Who")
}