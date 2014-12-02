package functor.hybrid

trait Functor[T[_], A] {
  def fmap[B](f: A=> B): T[B]
  val identity: A
}

case class ListFunctor[A](val identity: A, list: List[A]) extends Functor[List, A] {
  def fmap[B](f: A => B): List[B] = list.map(f)
}

object Test2 extends App {
  val lf = ListFunctor[Int](0, List(1, 2))
  println(lf.fmap(_ + 1))
}
