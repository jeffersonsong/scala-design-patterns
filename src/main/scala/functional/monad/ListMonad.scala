package functional.monad

import scala.collection.mutable.ListBuffer

case class ListMonad[A](val list: List[A]) extends Monad[List, A] {
  val identity = Nil

  def fmap(f: A => A): ListMonad[A] = ListMonad(list.map(f))

  def apply(tf: List[A => A]): ListMonad[A] = {
    var l: List[A] = list
    tf.foreach(f => l = l.map(f))
    ListMonad(l)
  }
  def append(l: List[A]) = ListMonad(list ++ l)
}

object ListMonad {
  def join[A](xs: List[ListMonad[A]]): ListMonad[A] = {
    val buffer = ListBuffer[A]()
    xs.foldLeft(buffer)((x, y) => x ++= y.list)
    ListMonad(buffer.toList)
  }
  def pure[A](a: A) = ListMonad(List(a))
}

object Test extends App {
  val monad = ListMonad[Int](List(1, 2))
  println(monad)
  println(monad append List(6, 7))
  println(monad fmap (_ + 1))

  val increase = (x: Int) => x + 1
  val double = (x: Int) => x * 2
  println(monad apply (List(increase)))
  println(monad apply (List(increase, double)))

  val l1 = List(ListMonad(List(1, 2)), ListMonad(List(5, 6)))
  println(ListMonad join l1)

  println(ListMonad pure 4)
}
