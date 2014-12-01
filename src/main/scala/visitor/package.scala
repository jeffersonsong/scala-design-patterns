/**
 * Created by jeffersons on 11/30/2014.
 */
package object visitor {
  trait Visitor

  trait Visitable {
    def accept(v: Visitor): Unit
  }
}
