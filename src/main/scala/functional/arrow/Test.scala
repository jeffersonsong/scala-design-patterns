package functional.arrow

object Test extends App {
  import Arrow._

  val f1 = (x: Int) => x / 2
  val a1 = Function1Arrow.arrow(f1)
  println(a1)
  println(a1(4))

  val f2 = (x: Int) => x * 3 + 1
  val a2 = Function1Arrow.arrow(f2)
  println(a2)
  println(a2(4))

  val a3 = Function1Arrow.compose(a1, a2)
  println(a3)
  println(a3(4))
}
