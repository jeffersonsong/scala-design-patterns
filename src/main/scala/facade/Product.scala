package facade

trait Product

case class EquityTrade(val id: String, val stock: String, val quantity: Double) extends Product

case class InterestRateSwap(val id: String, val nominal: Double, val fixedRate: Double, val floatIndex: String) extends Product

case class Loan(val id: String, val borrower: String, val facility: Double) extends Product