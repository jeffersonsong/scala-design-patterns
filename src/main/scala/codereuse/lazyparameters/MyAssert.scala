package codereuse.lazyparameters

object MyAssert {
  var assertionEnabled = true

  def basicAssert(predicate: Boolean) =
    if (assertionEnabled && !predicate) throw new AssertionError

  def byNameAssert(predicate: => Boolean) =
    if (assertionEnabled && !predicate) throw new AssertionError
}

object Test2 extends App {
  import MyAssert._

  //basicAssert(5 < 3)

  byNameAssert(5 < 3)
}
