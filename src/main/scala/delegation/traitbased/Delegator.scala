package delegation.traitbased

trait Delegate {
  def printer = println("Hello World")
}

case class Delegator extends Delegate {
  def print = printer
}

object Test extends App {
  val d = Delegator()
  println(d)
  d.print
}