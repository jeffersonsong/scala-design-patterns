package gof.structural.bridge

trait DAO[T] extends Abstraction {
  def persist(obj: T) : String
  def findById(id: String) : Option[T]
  def deleteById(id: String) : Unit
}
