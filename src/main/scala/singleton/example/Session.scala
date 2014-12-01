package singleton.example

trait Session {
  val id: String
}

private trait InternalSession extends Session {
  def postCreate: Unit
  def preDestroy: Unit
}

private class SessionImpl(val id: String) extends InternalSession {
  override def postCreate: Unit = println("SessionImpl postCreate")

  override def preDestroy: Unit = println("SessionImpl preDestroy")

  override def toString = "session " + id + " singleton"
}

object SessionManager {
  private[this] var _instance: Option[InternalSession] = None
  private[singleton] def instance: Session = {
    if (_instance isEmpty) {
      _instance = Option(new SessionImpl("jeh"))
      _instance.get.postCreate
    }
    _instance.get
  }

  def destry = {
    if (_instance nonEmpty) {
      _instance.get.preDestroy
      _instance = None
    }
  }

  def session: Session = {
    new SessionDelegate()
  }
}

private class SessionDelegate extends Session {
  override val id: String = {
    SessionManager.instance.id
  }

  override def toString = SessionManager.instance.toString
}

object Test extends App {
  val s1 = SessionManager.session
  println(s1)
  println("---------------------------")
  val s2 = SessionManager.session
  println(s2)
  println("---------------------------")
  SessionManager.destry
  println("---------------------------")
  println(s2)
  println("---------------------------")
  val s3 = SessionManager.session
  println(s3)
}
