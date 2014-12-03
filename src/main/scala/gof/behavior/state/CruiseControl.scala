package gof.behavior.state

case class CruiseEvent(val event: String) extends Event

abstract class CruiseState(label: String) extends State {
  def init = println("init " + label)

  override def toString = label
}

object OffState extends CruiseState("Off") {
  def handle(e: Event): State = e match {
    case CruiseEvent("on") => OnState
    case _ => throw new RuntimeException("Unsupported transition")
  }
}

object OnState extends CruiseState("On") {
  def handle(e: Event): State = e match {
    case CruiseEvent("off") => OffState
    case CruiseEvent("suspend") => SuspendState
    case _ => throw new RuntimeException("Unsupported transition")
  }
}

object SuspendState extends CruiseState("Suspend") {
  def handle(e: Event): State = e match {
    case CruiseEvent("resume") => OnState
    case CruiseEvent("off") => OffState
    case _ => throw new RuntimeException("Unsupported transition")
  }
}

case class CruiseControl extends Context {
  private[state] var state: State = OffState

  def turnOn: Unit = state = state.handle(CruiseEvent("on"))

  def turnOff: Unit = state = state.handle(CruiseEvent("off"))

  def suspend: Unit = state = state.handle(CruiseEvent("suspend"))

  def resume: Unit = state = state.handle(CruiseEvent("resume"))

  override def toString = "CruiseControl: " + state
}

object Test extends App {
  val cc = CruiseControl()
  println(cc)
  cc.turnOn
  println(cc)
  cc.suspend
  println(cc)
  cc.resume
  println(cc)
  cc.turnOff
  println(cc)
}