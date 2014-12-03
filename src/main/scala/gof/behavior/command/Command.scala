package gof.behavior.command

import scala.collection.mutable.ListBuffer

case class Executor(val history: ListBuffer[Command] = ListBuffer[Command]()) {
  def rerun = history.foreach(_())

  def execute(cmd: Command) = {history.append(cmd); cmd()}

  override def toString = "Executor: " + history
}

object Test extends App {
  val exec = Executor()
  val sayHello = () => println("Hello")
  exec.execute(sayHello)

  val x = 10
  exec.execute(() => println(x * 3))
  println(exec)
  exec.rerun
}
