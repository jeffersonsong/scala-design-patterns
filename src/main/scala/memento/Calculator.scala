package memento

case class CalculatorMemento(s: CalcState) extends Memento[CalcState] {
  _state = Option(s)
}

class Calculator extends Originator[CalcState] with CareTaker[CalcState] {
  var first = 0
  var second = 0
  var operator = "+"
  var result = 0.0

  def calculate = operator match {
    case "+" => result = first + second
    case "-" => result = first - second
    case "*" => result = first * second
    case "/" => result = first / second
  }

  def createMemento = CalculatorMemento(first, second, operator, result)

  def backupOperation = mementos push createMemento

  def restoreOperation = {
    val state = mementos.pop().state
    first = state._1
    second = state._2
    operator = state._3
    result = state._4
  }

  override def toString() = result + " = " + first + " " + operator + " " + second
}

object Test extends App {
  val calc = new Calculator()
  calc.first = 10
  calc.second = 20
  calc.calculate
  println(calc)

  calc.backupOperation
  calc.second = 5
  calc.calculate
  println(calc)

  calc.restoreOperation
  println(calc)
}