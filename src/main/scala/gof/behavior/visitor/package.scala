package gof.behavior

package object visitor {
  trait Visitor

  trait Visitable {
    def accept(v: Visitor): Unit
  }
}
