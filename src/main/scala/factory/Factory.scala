package factory

trait Product

case class ConcreteProduct(s: String) extends Product
case class AlternativeConcreteProduct(i: Int) extends Product

trait Factory {
  def create(a: Any): Product
}

object ProductFactory extends Factory {
  implicit def create(a: Any): Product = a match {
    case s: String => ConcreteProduct(s)
    case i: Int => AlternativeConcreteProduct(i)
  }
}

object Test extends App {
  val a = ProductFactory.create("John")
  println(a)
  val b = ProductFactory.create(32)
  println(b)
  import ProductFactory._
  val c: Product = "Adam"
  println(c)
  val d: Product = "Denise"
  println(d)
  val e: Product = 100
  println(e)
}
