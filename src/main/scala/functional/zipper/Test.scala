package functional.zipper

object Test extends App {
  val data = Seq("John", "Denise", "Phoebe", "Adam")
  var zip = zipper[String](Nil, data.head, data.tail)

  println(zip)
  println(zip.atStart)

  zip = zip.next.get
  println(zip)
  println(zip.atStart)

  zip = zip.prev.get
  println(zip)
  println(zip.atStart)
}
