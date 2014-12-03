package functional.view

object Test extends App {
  /*
  val r = (1 to 1000000000).filter(_ % 2 == 0).take(10).toList
  println(r)
  */
  val r = (1 to 1000000000).view.filter(_ % 2 == 0).take(10).toList
  println(r)

  val xs = List(3, 2, 3, 4, 5)
  val ys = xs.view map {x => println(x); x * x}
}
