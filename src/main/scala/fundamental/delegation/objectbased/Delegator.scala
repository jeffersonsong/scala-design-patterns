package fundamental.delegation.objectbased

case class Delegator {
  private val delegate = Delegate()
  def print = delegate.printer
  override def toString = "Delegator"
}

case class Delegate {
  def printer = println("Hello World")
}

object Test extends App {
  val d = Delegator()
  println(d)
  d.print
}