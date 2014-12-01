package observer

case class Logger extends Observer {
  def printer(e: ObservableEvent) = println(e)
}
