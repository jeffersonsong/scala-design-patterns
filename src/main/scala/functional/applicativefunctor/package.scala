package functional

package object applicativefunctor {
  trait Functor[T[_], A] {
    def fmap[B](f: A => B): T[B]
    val identity: A
  }

  trait ApplicativeFunctor[T[_], A] extends Functor[T, A] {
    def apply(tf: T[A => A]): T[A]
  }
}
