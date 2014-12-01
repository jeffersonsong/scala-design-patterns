package stackabletraits

object StackableTraitsTest2 {
  def main(args: Array[String]): Unit = {
    val queue = new BasicIntQueue with Incrementing with Doubling
    queue.put(10)
    println(queue.get())
  }
}
