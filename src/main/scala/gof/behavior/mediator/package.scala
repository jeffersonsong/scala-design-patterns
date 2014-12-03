package gof.behavior

import scala.collection.mutable.ListBuffer

package object mediator {
  trait Mediator {
    type ColleagueCallback
    var colleagues = ListBuffer[ColleagueCallback]()
    def notify(any: Any) : Unit
  }

  trait Colleague
}
