package exercice2

object Exercice2 {

  sealed trait Article

  case class Regular(name: String, category: String, price: Double) extends Article

  // discount est compris entre 0 et 1
  case class Discounted(name: String, category: String, price: Double, discount: Float) extends Article

  def isDiscounted(article: Article): Boolean = article match {
    case Discounted(_, _, _, _) => true
    case Regular(_, _, _) => false
  }

  def discountedItems(articles: List[Article]): List[Discounted] = articles.collect {
    case d: Discounted => d
  }

  def regularItems(articles: List[Article]): List[Regular] = articles.collect {
    case r: Regular => r
  }

  def applyDiscount(article: Discounted): Discounted = article.copy(price = article.price * (1.0f - article.discount))

  def cartAmount(articles: List[Article], coupon: Article => Double): Double = articles.map {
    case a: Regular => coupon(a)
    case b: Discounted => coupon(applyDiscount(b))
  }.sum

  def applyCoupon(coupon: Float, category: String): Article => Double = {
    case a: Regular => if (a.category == category) a.price * (1.0f - coupon) else a.price
    case b: Discounted => if (b.category == category) b.price * (1.0f - coupon) else b.price
  }
}