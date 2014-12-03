package gof.creational.builder

import scala.collection.mutable.ListBuffer

trait Document extends Product

trait TextDocument extends Document {
  private var _text = ListBuffer[String]()
  def reset: Unit = _text = ListBuffer[String]()
  def text = _text
}

trait TextBuilder extends Builder {
  private var count = 0
  def title(t: String) = "title: " + t + "\n"
  def author(a: String) = "by " + a + "\n"
  def heading(h: String) = {count += 1; count + ". " + h + "\n"}
  def para(p: String) = p + "\n"
}

case class TextDocumentDirector extends  Director with TextDocument with TextBuilder {
  def addTitle(s: String): Unit = text += title(s)
  def addAuthor(s: String): Unit = text += author(s)
  def addHeading(s: String): Unit = text += heading(s)
  def addPara(s: String): Unit = text += para(s)
}

object Test extends App {
  val director = TextDocumentDirector()
  director.addTitle("Scala in Depth")
  director.addAuthor("John Smith")
  director.addHeading("Introduction")
  director.addPara("This is Scala.")
  println(director.text.mkString(" "))
}
