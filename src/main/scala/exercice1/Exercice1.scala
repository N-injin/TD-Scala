package exercice1

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.math.min

object Exercice1 {
  def compteMots(chaine: String): Int = {
    def mySplit(chaine: String, char: Char): List[String] = {
      var previous = 0
      var result = new ListBuffer[String]()
      for (i <- 0 until chaine.length) {
        if (chaine(i) == char) {
          result += chaine.slice(previous, i)
          previous = i
        }
      }
      result += chaine.slice(previous, chaine.length)
      result.toList
    }

    mySplit(chaine, ' ').count(_ != "")
  }

  def myMin(a: List[Int]): Int = {
    if (a.isEmpty) {
      throw new IllegalArgumentException
    }

    @tailrec
    def minHelper(a: List[Int], m: Int): Int = a match {
      case head :: Nil => min(head, m)
      case head :: tail => minHelper(tail, min(head, m))
      case Nil => throw new IllegalArgumentException
    }

    minHelper(a, a.head)
  }

  @tailrec
  def egaux(a: List[Int], b: List[Int]): Boolean = (a, b) match {
    case (h1 :: Nil, h2 :: Nil) => h1 == h2
    case (h1 :: t1, h2 :: t2) => h1 == h2 && egaux(t1, t2)
    case (_, _) => false
  }
}