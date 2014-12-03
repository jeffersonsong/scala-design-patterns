package gof.behavior.chainofresponsibility.processingchain

import scala.collection.mutable.ListBuffer

trait Handler[T] {
  def handle(obj: T) : Unit
}

case class ChainController[T] {
  private val chain = ListBuffer[Handler[T]]()
  def add(h: Handler[T]) = {chain += h; ChainController.this}
  def apply(obj: T) = {
    chain.foreach(_ handle obj)
  }
}

case class Adder() extends Handler[String] {
  override def handle(obj: String) = println("+" + obj)
}

case class Subtractor() extends Handler[String] {
  override def handle(obj: String) = println("-" + obj)
}

object Test extends App {
  val controller = ChainController[String]
  controller.add(Adder()).add(Subtractor())
  controller.apply("John")
  controller.apply("Bob")
}