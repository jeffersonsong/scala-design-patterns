package fundamental.marker

case class Basic extends Marker

case class Named(val id: String) extends Marker

object Test extends App {
  println(Basic())
  println(Named("ABC123"))
}
