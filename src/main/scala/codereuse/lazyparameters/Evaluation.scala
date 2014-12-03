package codereuse.lazyparameters

object Evaluation {
  def eagerEval(x: Int) = {println("eager"); x}
  def lazyEval(x: => Int) = {println("lazy"); x}
}

object Test extends App {
  import Evaluation._

  def answer = {println("answer"); 40}
  eagerEval(answer + 2)
  println("-------------")
  lazyEval(answer + 2)
}