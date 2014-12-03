package gof.creational.abstractfactory

import scala.collection.immutable.HashMap

trait Window extends Product {
  val title: String
  var x: Int
  var y: Int
}

trait Menu extends Product {
  val title: String
  val shortcut: String
}

trait UIFactory extends Factory {
  def window(title: String): Window
  def menu(title: String, shortcut: String): Menu
}

case class OSXFactory extends UIFactory {
  class OSXMenu(val title: String, val shortcut: String) extends Menu {
    override def toString = "OSXMenu: " + title + " (" + shortcut + ")"
  }

  class OSXWindow(val title:String, var x: Int = 0, var y: Int = 0) extends Window {
    override def toString = "OSXWindow: " + title + "(" + x +"," + y + ")"
  }

  def window(title:String) = new OSXWindow(title)

  def menu(title:String, shortcut: String) = new OSXMenu(title, shortcut)
}

case class WinFactory extends UIFactory {
  class WinMenu(val title: String, val shortcut: String) extends Menu {
    override def toString = "WinMenu: " + title + " (" + shortcut + ")"
  }

  class WinWindow(val title:String, var x: Int = 0, var y: Int = 0) extends Window {
    override def toString = "WinWindow: " + title + "(" + x +"," + y + ")"
  }

  def window(title:String) = new WinWindow(title)

  def menu(title:String, shortcut: String) = new WinMenu(title, shortcut)
}

object FactoryProvider {
  private val default = OSXFactory()
  private val factories = HashMap[String, UIFactory]("WIN"->WinFactory(), "OSX"->default)
  def factory = factories.getOrElse(System.getProperty("gof/creational/factory"), default)
}

object Test extends App {
  val f = FactoryProvider.factory
  println(f.window("Main"))
  println(f.menu("File", "CTRL-F"))
}