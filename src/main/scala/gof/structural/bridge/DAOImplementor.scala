package gof.structural.bridge

trait DAOImplementor[T] extends Implementor {
  def save(obj: T): String
  def delete(id: String) : Unit
  def get(id: String) : Option[T]
}
