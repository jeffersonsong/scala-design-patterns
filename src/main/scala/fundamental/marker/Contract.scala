package fundamental.marker

import java.util.Date

trait Contract

case class TradeContract(val buyer: String, val seller: String, val notional: Double,
                         val fixRate: Double, val floatingIndex: String, val effectiveDate: Date,
                         val term: Int) extends Contract {
  var floatingRate: Double = 0.0
}

case class LoanContract(val lender: String, val borrower: Seq[String], val amount: Double,
                         val rate: Double, val startDate: Date, val duration: Int, val repayments: String) extends Contract {
  def totalRepayable = {
    amount * Math.pow((1 + rate), duration)
  }
}

object Test2 extends App {
  val c1 = LoanContract("BankCo", Seq("John"), 1000, 0.05, new Date(), 3, "monthly")
  println(c1)
  println(c1.totalRepayable)
  println("-------------------")
  val c2 = TradeContract("JayCo", "DeeCo", 100000, 1.4, "Libor", new Date(), 10)
  println(c2)
  c2.floatingRate = 0.7
}