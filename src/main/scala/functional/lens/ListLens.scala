package functional.lens

case class ListLens[A]() extends Lens[List, A] {
  def get(list: List[A]): A = list.head
  def set(list: List[A], b: A): List[A] = list :+ b
}

object Test extends App {
  var l = List(1, 2)
  val lens = new ListLens[Int]()
  println(lens.get(l))
  l = lens.set(l, 3)
  println(l)
}
