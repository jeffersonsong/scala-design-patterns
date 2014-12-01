package immutability

trait Product extends Immutable {
  val price : Double
}

case class Invoice[T <: Product](val id: Int, val name: String, val contents: List[T], val version: Version = Version(0)) extends Immutable {
  def total = contents.map(_.price).sum
  def +(p: T) = Invoice(id, name, p :: contents, version.next)
  def -(p: T) = Invoice(id, name, contents diff List(p), version.next)
}

case class Version(val number : Int) extends Immutable {
  override def toString = "v" + number
  def next = copy(number + 1)
}

case class Book(val title: String, val author: String, val price: Double) extends Product

object Test extends App {
  val books = List(Book("Java", "John", 12.99))
  var i = Invoice(1, "Denise", books)
  println(i)
  i = i + Book("Scala", "Adam", 10.99)
  println(i)
}
