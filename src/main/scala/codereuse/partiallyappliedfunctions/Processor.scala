package codereuse.partiallyappliedfunctions

object Processor {
  val apply = (func: Int => Int, x: Int) => func(x)
}

object Test extends App {
  import Processor._
  val f = (x:Int) => x * 2
  println(apply(f, 2))

  val double = apply(f, _: Int)
  println(double(2))
}
