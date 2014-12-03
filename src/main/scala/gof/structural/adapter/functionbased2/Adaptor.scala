package gof.structural.adapter.functionbased2

class Adaptee(title: String, name: String) {
  def func1(s: String) = s + " " + name
  def func2(s: String) = s + " " + title
}

object Adaptor extends Adaptee("Dr", "Who") {
  val addAndPrint = func1 _ compose func2 _
}

object Client extends App {
  println(Adaptor.addAndPrint("->"))
}
