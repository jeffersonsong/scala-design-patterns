package functional

package object foldable {
  trait Foldable[T[_], A] {
    val identity: A
    def fold(op: (A, A) => A): A
  }
}
