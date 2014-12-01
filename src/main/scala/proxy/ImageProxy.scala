package proxy

import javax.swing.ImageIcon

trait ImageProvider {
  val filename: String
  def image: ImageIcon
}

case class ImageProxy(filename: String) extends Proxy with ImageProvider {
  println("Creating ImageProxy")

  private lazy val service = new ImageServiceImpl(filename)
  def image: ImageIcon = service.image
  override def toString = "Proxy for: " + filename
}

private class ImageServiceImpl(val filename: String) extends Service with ImageProvider {
  println("Creating ImageServiceImpl")
  private val _image = new ImageIcon(filename)
  def image: ImageIcon = _image
}

object Test extends App {
  val proxy = ImageProxy("help.jpg")
  println(proxy)
  val image = proxy.image
  println(image)
}
