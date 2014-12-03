package gof.behavior

import scala.collection.mutable.Stack

package object memento {
  trait Memento[A] {
    protected var _state: Option[A] = None
    def state = _state.get
  }

  trait Originator[A] {
    def createMemento(): Memento[A]
  }

  trait CareTaker[A] {
    val mementos = Stack[Memento[A]]()
  }

  type CalcState = Tuple4[Int, Int, String, Double]
}
