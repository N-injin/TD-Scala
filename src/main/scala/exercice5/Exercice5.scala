package exercice5

object Exercice5 {
  sealed trait Liste[+A] {
    def concat[B >: A](list: Liste[B]): Liste[B]
    def map[B](f: A => B): Liste[B]
    def flatMap[B](f: A => Liste[B]): Liste[B]
    def fold[B >: A](default: B, f: (B, B) => B): B
  }

  case class NonVide[+A](tete: A, queue: Liste[A]) extends Liste[A] {
    override def concat[B >: A](list: Liste[B]): Liste[B] = list match {
      case Vide => this
      case items: NonVide[B] => this.copy(queue=queue.concat(items))
    }

    override def map[B](f: A => B): Liste[B] = NonVide(f(tete), queue.map(f))

    override def flatMap[B](f: A => Liste[B]): Liste[B] = f(tete).concat(queue.flatMap(f))

    override def fold[B >: A](default: B, f: (B, B) => B): B = {
      val t = f(default, tete)
      queue.fold(t, f)
    }
  }

  case object Vide extends Liste[Nothing] {
    override def concat[B >: Nothing](list: Liste[B]): Liste[B] = list

    override def map[B](f: Nothing => B): Liste[B] = this

    override def flatMap[B](f: Nothing => Liste[B]): Liste[B] = this

    override def fold[B >: Nothing](default: B, f: (B, B) => B): B = default
  }

  object Liste {
    def apply[A](first: A, others: A*): Liste[A] = (first, others) match {
      case (Nil, Nil) => Vide
      case (item, Nil) => NonVide[A](item, Vide)
      case (item, o) => NonVide[A](item, Liste[A](o.head, o.tail:_*))
    }
  }
}
