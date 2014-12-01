package bridge

import scala.collection.mutable.HashMap

trait PersonDAO extends DAO[Person]{
  self : DAOImplementor[Person] =>
  def deleteById(id: String) : Unit = this.delete(id)
  def findById(id: String) : Option[Person] = this.get(id)
  def persist(obj: Person) : String = this.save(obj)
}

trait InMemoryDAOImplementor extends DAOImplementor[Person] {
  private val data = HashMap[String, Person]()
  private var count = 0
  def save(obj: Person) : String = {
    count += 1
    val id = "p" + count
    data.put(id, obj)
    id
  }

  def delete(id: String) = data.remove(id)
  def get(id: String) : Option[Person] = data.get(id)
}

object PersonalDAL extends PersonDAO with InMemoryDAOImplementor

object Test extends App {
  println("Create Person")
  val p1 = Person("John", 49)
  println(p1)
  println("Persist Person")
  val id = PersonalDAL.persist(p1)
  println("Retrieve Person by Id")
  val p2 = PersonalDAL.findById(id)
  println(p2)
  println("Delete Person")
  PersonalDAL.deleteById(id)
  println("Try to find again by Id")
  val p3 = PersonalDAL.findById(id)
  println(p3)
}
