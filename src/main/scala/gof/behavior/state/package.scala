package gof.behavior

package object state {
  trait Event

  trait State {
    def init: Unit
    def handle(e: Event) : State
  }

  trait Context {
    private[state] var state : State
  }
}
