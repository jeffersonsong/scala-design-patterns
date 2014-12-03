package functional

package object lens {
  trait Lens[T[A], A] {
    def get(t: T[A]): A
    def set(t: T[A], a: A): T[A]
  }
}
