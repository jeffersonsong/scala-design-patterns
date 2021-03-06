package functional.monoid

case class StringMonoid() extends Monoid[String] {
  def append(s1: String, s2: String) = s1 + s2

  val identity = ""
}

object IntMonoid extends Monoid[Int] {
  def append(x: Int, y: Int) = x + y

  val identity = 0
}

object Test extends App {
  val stringMonoid = StringMonoid()
  println(stringMonoid.append(stringMonoid.identity, "John"))
  println(stringMonoid.append("John", "Hunt"))

  println(IntMonoid.append(IntMonoid.identity, 1))
  println(IntMonoid.append(1, 2))
  println(IntMonoid.append(2, 1))
}
