package gof.behavior.strategy

trait Calculator {
  private[strategy] var strategy : Strategy
  def calc(o: Person) : Long = strategy(o)
}

case class TaxCalculator extends Calculator {
  var strategy = (e: Person) => Math.ceil(e.salary * 0.3).toLong
}

object Test extends App {
  val p = Person(24000)
  val tc = TaxCalculator()
  println(tc.calc(p))

  tc.strategy = (e:Person) => Math.ceil(e.salary * 0.5).toLong
  println(tc.calc(p))
}