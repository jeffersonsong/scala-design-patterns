package functional

package object monad {
  trait Functor[T[_], A] {
    def fmap(func: A => A): Functor[T, A]
    val identity: T[A]
  }

  trait ApplicativeFunctor[T[_], A] extends Functor[T, A] {
    def apply(functions: T[A => A]): ApplicativeFunctor[T, A]
  }

  trait Monoid[T[_], A] {
    def append(values: T[A]): Monoid[T, A]
  }

  trait Monad[T[_], A] extends Monoid[T, A] with ApplicativeFunctor[T, A]
}
