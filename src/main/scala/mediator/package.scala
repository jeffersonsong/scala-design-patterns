import scala.collection.mutable.ListBuffer

/**
 * Created by jeffersons on 11/30/2014.
 */
package object mediator {
  trait Mediator {
    type ColleagueCallback
    var colleagues = ListBuffer[ColleagueCallback]()
    def notify(any: Any) : Unit
  }

  trait Colleague
}
