package visitor

import scala.collection.mutable.ListBuffer

case class TOC {
  val contents = ListBuffer[String]()

  def ++(s: String) = contents append s

  def isEmpty = contents.isEmpty

  override def toString: String = "TOC: " + contents.toString
}

case class ChapterVisitor(chapters: ListBuffer[Chapter], var count: Int = 1) extends Visitor {
  val toc: TOC = TOC()

  def toTOC: TOC = {
    if (toc.isEmpty) chapters.foreach(
      (c: Chapter) => {
        c.accept(ChapterVisitor.this); count += 1
      }
    )
    toc
  }
}

case class Chapter(val title: String, val headings: Set[String]) extends Visitable {
  def accept(v: Visitor) = v match {
    case tocVisitor: ChapterVisitor => {
      tocVisitor.toc ++ (tocVisitor.count + "." + this.title)
      var headingCount = 1
      headings.foreach(
        (heading: String) => {
          tocVisitor.toc ++ (tocVisitor.count + "." + headingCount + " " + heading)
          headingCount += 1
        }
      )
    }
  }
}

case class Document {
  val chapters = new ListBuffer[Chapter]()

  def tableOfContents: TOC = ChapterVisitor(chapters).toTOC
}

object Test extends App {
  val doc = Document()
  val c1 = Chapter("Introduction", Set("One", "Two", "Three"))
  val c2 = Chapter("Scala", Set("History", "Influences", "Sample"))
  doc.chapters.append(c1)
  doc.chapters.append(c2)
  println(doc.tableOfContents)
}
