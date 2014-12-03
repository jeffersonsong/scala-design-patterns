package functional.applicativefunctor

abstract class ListApplicativeFunctor[A](list: List[A]) extends ApplicativeFunctor[List, A] {
  def fmap[B](f: A => B): List[B] = list.map(f)

  def apply(tf: List[A => A]): List[A] = {
    var l: List[A] = list
    tf.foreach(f => l = l.map(f))
    l
  }
}

case class IntListAppFunctor(list: List[Int], val identity: Int = 0)
    extends ListApplicativeFunctor[Int](list)

object IntListAppFunctor {
  implicit def pure(a: Int) = IntListAppFunctor(List[Int](a))
}

object Test extends App {
  val af = IntListAppFunctor(List(1, 2))
  val increase = (x: Int) => x + 1
  val double = (x: Int) => x * 2

  println(af.apply(List(increase)))
  println(af.apply(List(increase, double)))

  println(IntListAppFunctor.pure(23))
}
