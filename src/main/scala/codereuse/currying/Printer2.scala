package codereuse.currying

import java.io.{PrintWriter, File}
import java.util.Date

object Printer2 {
  def write(file: File)(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}

object Test2 extends App {
  val file = new File("date.txt")
  //Printer2.write(file)(writer => writer.println(new Date()))

  /*Printer2.write(file) {
    writer => writer.println(new Date())
  }*/

  val fileWriter = Printer2.write(file)_

  /*
  fileWriter {
    writer => writer.println(new Date())
  }*/

  fileWriter {
    _ println new Date()
  }
}
