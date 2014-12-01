package mediator

class Event(val source: Colleague, val description: String)
case class ActionEvent(s: Colleague, d: String) extends Event(s, d)
case class SaveEvent(s: Colleague, d: String) extends Event(s, d)
case class MouseEvent(s: Colleague, val x: Int, val y: Int, d: String) extends Event(s, d)

class EventMediator extends Mediator {
  type ColleagueCallback = Event => Unit
  def notify(e: Any) = e match {
    case event : Event => (colleagues clone) foreach(_(event))
    case _ => throw new RuntimeException("Invalid Mediator type")
  }
}

object EventMediator {
  val instance = new EventMediator()
}

object LogPanel extends Colleague {
  def log(e: Event): Unit = println("Logger: " + e)
}

object Form extends Colleague {
  def receive(e: Event) = println("Form Received event: " + e)
  def update = {
    println("Input changed")
    EventMediator.instance.notify(ActionEvent(this, "Update doc"))
  }
  def save = EventMediator.instance.notify(SaveEvent(this, "Saved"))
}

object Frame extends Colleague {
  var requiresSave = false
  def handleEvent(e: Event) : Unit = e match  {
    case e: MouseEvent if e.source == this => println("Do nothing event from itself")
    case _: MouseEvent => println("Some other MouseEvent")
    case _: ActionEvent => requiresSave = true; println("Frame Requires save")
    case _: SaveEvent => requiresSave = false; println("Frame Save reset")
    case _ => println("Frame received 'other' Event")
  }
  def input = EventMediator.instance.notify(MouseEvent(this, 10, 10, "Clicked"))
}

object Test extends App {
  val mediator = EventMediator.instance
  mediator.colleagues.append(LogPanel.log)
  mediator.colleagues.append(Frame.handleEvent)
  mediator.colleagues.append(Form.receive)
  Frame.input
  Form.update
  Form.save
}