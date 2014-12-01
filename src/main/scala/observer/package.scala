import scala.collection.mutable.ListBuffer

/**
 * Created by jeffersons on 11/30/2014.
 */
package object observer {
  trait Observer

  case class ObservableEvent(val observable: Observable)

  trait Observable {
    type ObserverCallback = ObservableEvent => Unit
    val observers = ListBuffer[ObserverCallback]()

    def changed = {
      val event = ObservableEvent(this)
      observers.foreach(_(event))
    }
  }
}
