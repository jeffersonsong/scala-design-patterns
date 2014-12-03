package gof.behavior

package object strategy {
  case class Person(val salary: Long)

  type Strategy = Person => Long
}
