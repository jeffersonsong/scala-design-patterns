package functional.foldable

abstract class FoldableList[A] extends Foldable[List, A] {
  private[foldable] val list: List[A]
  def fold(op: (A, A) => A): A = list.foldLeft(identity)(op)
}

case class FoldableIntList(val list: List[Int]) extends FoldableList[Int] {
  val identity = 0
}

case class FoldableStringList(val list: List[String]) extends FoldableList[String] {
  val identity = ""
}

object Test extends App {
  val foldable = FoldableIntList(List(1, 2, 3, 4))
  println(foldable.fold(_ + _))

  val strings = FoldableStringList(List("A", "B", "C"))
  println(strings.fold(_ + _))
}