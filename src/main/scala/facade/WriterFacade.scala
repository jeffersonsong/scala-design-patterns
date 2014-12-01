package facade


object WriterFacade {
  def save(product: Product) = product match {
    case trade: EquityTrade => {
      MongoDBWriter.write(trade); TerradataWriter.save(trade)
    }
    case irs: InterestRateSwap => {
      TerradataWriter.save(irs); DefaultWriter.write(irs)
    }
    case loan: Loan => DB2Writer.store(loan)
    case _ => DefaultWriter.write(product)
  }
}

object DefaultWriter {
  def write(p: Product): Unit = println("Default writer: " + p)
}

object MongoDBWriter {
  def write(p: Product): Unit = println("MongoDBWriter.write: " + p)
}

object TerradataWriter {
  def save(product: Product): Unit = println("TerradataWriter.save: " + product)
}

object DB2Writer {
  def store(loan: Loan): Unit = println("DB2Writer.store: " + loan)
}

object Test extends App {
  val trade = EquityTrade("1", "IBM", 100)
  val irs = InterestRateSwap("2", 10000, 1.4, "LIBOR")
  val loan = Loan("3", "John", 1000)
  WriterFacade.save(trade)
  WriterFacade.save(irs)
  WriterFacade.save(loan)
}