package gof.behavior.chainofresponsibility.chainmanager

import scala.collection.mutable.ListBuffer

trait Handler[T] {
  def accept(obj: T) : Boolean
  def handle(obj: T) : Unit
}
case class ChainController[T] {
  private val chain = ListBuffer[Handler[T]]()
  def add(h: Handler[T]) = {chain += h; ChainController.this}
  def apply(obj: T) = {
    val handler = Option(chain.filter(n => n accept obj) head)
    handler match {
      case Some(chainElement) => chainElement.handle(obj)
      case None => println("Can't handle")
    }
  }
}

object Printer1 extends Handler[String] {
  def accept(obj: String) = obj equals "John"
  def handle(obj: String) = println("HI John")
}

object Printer2 extends Handler[String] {
  def accept(obj: String) = obj != "John"
  def handle(obj: String) = println(obj)
}

object Example2 extends App {
  val controller = ChainController[String]
  controller.add(Printer1).add(Printer2)
  controller.apply("John")
  controller.apply("Bob")
}
