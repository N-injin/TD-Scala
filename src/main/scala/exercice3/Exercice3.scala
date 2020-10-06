package exercice3

object Exercice3 {
  sealed abstract class Optional[+A] {
    def toList: List[A]
    def orElse[B >: A](alternative: => Optional[B]): Optional[B]
    def getOrElse[B >: A](default: B): B
  }

  case object Nul extends Optional[Nothing] {
    override def toList: List[Nothing] = Nil

    override def orElse[B >: Nothing](alternative: => Optional[B]): Optional[B] = alternative

    override def getOrElse[B >: Nothing](default: B): B = default
  }

  case class NonNul[+A](item: A) extends Optional[A] {
    private val _value: A = item

    override def toList: List[A] = _value :: Nil

    override def orElse[B >: A](alternative: => Optional[B]): Optional[B] = this

    override def getOrElse[B >: A](default: B): B = _value
  }

  object Optional {
    def fromNullable[A](nullable: A): Optional[A] = nullable match {
      case Nil => Nul
      case null => Nul
      case Nul => Nul
      case _ => NonNul(nullable)
    }
  }
}
