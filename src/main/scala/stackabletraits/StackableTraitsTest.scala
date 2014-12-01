package stackabletraits

object StackableTraitsTest {
  def main(args: Array[String]): Unit = {
    val queue = new BasicIntQueue with Doubling
    queue.put(10)
    println(queue.get())
  }
}
