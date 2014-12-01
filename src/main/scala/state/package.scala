/**
 * Created by jeffersons on 11/30/2014.
 */
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
