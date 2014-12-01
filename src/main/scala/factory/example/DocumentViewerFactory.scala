package factory.example

import scala.collection.mutable.HashMap

trait DocumentViewer {
  var filename: String
}

trait Factory {
  def create(s: String): DocumentViewer
}

private case class WordViewer(var filename: String) extends DocumentViewer

private object WordViewer {
  def create(s: String) = WordViewer(s)
}

private case class OpenOfficeViewer(var filename: String) extends DocumentViewer

private object OpenOfficeViewer {
  def create(s: String) = OpenOfficeViewer(s)
}

private case class JPEGViewer(var filename: String) extends DocumentViewer

private object JPEGViewer {
  def create(s: String) = JPEGViewer(s)
}

object DocumentViewerFactory extends Factory {
  private val viewers = HashMap[String, String => DocumentViewer]()

  viewers.put(".docx", WordViewer.create)
  viewers.put(".odt", OpenOfficeViewer.create)
  viewers.put(".jpg", JPEGViewer.create)

  def create(s: String): DocumentViewer = {
    var pos = s.lastIndexOf(".")
    if (pos < 0) {
      pos = 0
    }
    val endsWith = s.substring(pos)
    val funcOption = viewers.get(endsWith)
    if (funcOption.nonEmpty) {
      val func = funcOption.get
      func(s)
    } else {
      throw new RuntimeException("Unknown Document type")
    }
  }
}

object Test extends App {
  val p1 = DocumentViewerFactory.create("info.docx")
  println(p1)
  val p2 = DocumentViewerFactory.create("info.odt")
  println(p2)
  val p3 = DocumentViewerFactory.create("info.jpg")
  println(p3)
}
