package gof.behavior.observer

case class SharePriceWatcher(val share: String) extends Observable {
  private var _value : Double = 0.0
  def value = _value
  def value_=(value:Double) : Unit = {
    _value = value
    changed
  }
  override def toString(): String = "current value: " + _value
}

object Test extends App {
  val watcher = new SharePriceWatcher("GOOG")
  val logger = Logger()
  watcher.observers.append(logger.printer)
  watcher.value = 2.0
}