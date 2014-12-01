package adapter

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing.JButton

object AdapterManager {
  implicit def func2ActionListener(f: ActionEvent => Unit) =
    new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = f(e)
    }
}

object Test2 extends App {
  import AdapterManager._

  val b = new JButton()
  b.addActionListener((_: ActionEvent) => println("pressed"))
}