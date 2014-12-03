package gof.structural.flyweight

import scala.collection.mutable.HashMap

trait Account extends Flyweight with Immutable {
  val number: String
  val counterparty: String
  val valid: Boolean
}

case class CorporateAccount(val number: String, val counterparty: String, val valid: Boolean = true) extends Account {
  println("Creating Corporate Account: " + number)
}

object AccountFlyweightFactory {
  val map = HashMap[String, Account]()

  def get(number: String): Account = {
    return map.getOrElseUpdate(number, CorporateAccount(number, retrieveName(number)))
  }

  private def retrieveName(number: String): String = {
    return "John" + number
  }
}

object Test extends App {
  val a1 = AccountFlyweightFactory.get("111")
  println(a1)
  val a2 = AccountFlyweightFactory.get("222")
  println(a2)
  val a3 = AccountFlyweightFactory.get("111")
  println(a3)
  println(a1.eq(a3))
}
