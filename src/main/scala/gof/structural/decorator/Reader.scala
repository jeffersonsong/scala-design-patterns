package gof.structural.decorator

import java.io.FileInputStream
import java.util.Scanner

trait Reader {
  type T
  def read: T
}

class FileReader(file: String) extends Reader with Service {
  private val scanner = new Scanner(new FileInputStream(file))
  type T = Int
  override def read: Int = {
    scanner.nextInt()
  }
}

trait SynchronizedReader extends Reader with Decorator {
  abstract override def read: T = synchronized(super.read)
}

trait BufferedReader extends Reader with Decorator {
  abstract override def read: T = {
    println("Buffering the read")
    super.read
  }
}

object Test extends App {
  val f = new FileReader("DecoratorTest.txt")
  println(f.read)
  val syncReader = new FileReader("DecoratorTest.txt") with SynchronizedReader
  println(syncReader.read)
  val bsReader = new FileReader("DecoratorTest.txt") with BufferedReader with SynchronizedReader
  println(bsReader.read)
}