package functional.functor.functionbased

trait Functor[T[_]] {
  def fmap[A, B](list: T[A])(f: A=> B): T[B]
}

object ListFunctor extends Functor[List] {
  override def fmap[A, B](list: List[A])(f: A => B): List[B] = list map f
}

object Test extends App {
  val l1 = List(1, 2)
  val result = ListFunctor.fmap(l1)((i: Int) => i + 1)
  println(result)
}