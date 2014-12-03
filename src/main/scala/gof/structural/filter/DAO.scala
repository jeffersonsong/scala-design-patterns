package gof.structural.filter

import scala.collection.mutable.HashMap

trait DAO[T] {
  def save(a: T): Unit

  def delete(a: T): Unit

  def get(id: String): Option[T]
}

case class Person(val id: String, val name: String, var age: Int)

class PersonDAO extends DAO[Person] {
  private val data = HashMap[String, Person]()

  def save(p: Person): Unit = {
    println("PersonDAO.save");
    data.put(p.id, p)
  }

  def delete(p: Person): Unit = {
    println("PersonDAO.delete");
    data.remove(p.id)
  }

  def get(id: String): Option[Person] = {
    println("PersonDAO.get");
    data.get(id)
  }
}

trait DAOLoggerFilter[T] extends Filter with DAO[T] {
  abstract override def save(a: T): Unit = {
    println("Logger save: " + a)
    super.save(a)
  }

  abstract override def delete(a: T): Unit = {
    println("Logger delete: " + a)
    super.delete(a)
  }

  abstract override def get(id: String): Option[T] = {
    println("Logger get: " + id)
    super.get(id)
  }
}

trait DAOPerformanceFilter[T] extends Filter with DAO[T] {
  abstract override def save(a: T): Unit = {
    println("Starting save performance monitoring")
    val start = System.nanoTime
    super.save(a)
    printDuration(start)
  }

  abstract override def delete(a: T): Unit = {
    println("Starting delete performance monitoring")
    val start = System.nanoTime
    super.delete(a)
    printDuration(start)
  }

  abstract override def get(id: String): Option[T] = {
    println("Starting get performance monitoring")
    val start = System.nanoTime
    val obj = super.get(id)
    printDuration(start)
    obj
  }

  private def printDuration(start: Double) = {
    val diff = (System.nanoTime() - start) / 1000;
    println("Performance " + diff + " microseconds")
  }
}

object Test extends App {
  val dao = new PersonDAO with DAOPerformanceFilter[Person] with DAOLoggerFilter[Person]
  dao.save(Person("1", "John", 49))
  val p = dao.get("1")
  if (p.nonEmpty) dao.delete(p.get)
}