package codereuse.currying

import java.io.{File, PrintWriter}
import java.util.Date

object Printer1 {
  def write(file: File, op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}

object Test1 extends App {
  Printer1.write(new File("date.txt"), writer => writer.println(new Date()))
}
