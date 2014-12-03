package functional

package object monoid {
  trait Monoid[T] {
    def append(m1: T, m2: T): T
    val identity: T
  }
}
