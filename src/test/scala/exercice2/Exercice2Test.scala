package exercice2

import Exercice2.{
  Article,
  Discounted,
  Regular,
  isDiscounted,
  discountedItems,
  regularItems,
  applyCoupon,
  applyDiscount,
  cartAmount
}

class Exercice2Test extends org.scalatest.funsuite.AnyFunSuite {
  def ~=(x: Double, y: Double, precision: Double): Boolean = {
    if ((x - y).abs < precision) true else false
  }

  test("isDisounted on discounted item should return true") {
    val d = Discounted("Carrot", "Food", 1.5, 0.5f)

    assert(isDiscounted(d))
  }

  test("isDiscounted on regular item should return false") {
    val r = Regular("Carrot", "Food", 1.5)

    assert(!isDiscounted(r))
  }

  test("discountedItems on a list of Article should return the filtered discounted articles") {
    val articles: List[Article] = List(
      Regular(name = "Rice", category = "food", price = 10.0),
      Discounted(name = "Chocolate", category = "food", price = 8.0, discount = 0.1f),
      Regular(name = "Biscuits", category = "food", price = 2.0),
      Discounted(name = "Monitor", category = "tech", price = 119.99, discount = 0.1f),
      Discounted(name = "Mouse", category = "tech", price = 25.50, discount = 0.2f),
      Regular(name = "dress", category = "clothes", price = 49.90)
    )
    val filteredList: List[Article] = List(
      Discounted(name = "Chocolate", category = "food", price = 8.0, discount = 0.1f),
      Discounted(name = "Monitor", category = "tech", price = 119.99, discount = 0.1f),
      Discounted(name = "Mouse", category = "tech", price = 25.50, discount = 0.2f),
    )

    assert(discountedItems(articles) == filteredList)
  }

  test("regularItems on a list of Article should return the filtered regular articles") {
    val articles: List[Article] = List(
      Regular(name = "Rice", category = "food", price = 10.0),
      Discounted(name = "Chocolate", category = "food", price = 8.0, discount = 0.1f),
      Regular(name = "Biscuits", category = "food", price = 2.0),
      Discounted(name = "Monitor", category = "tech", price = 119.99, discount = 0.1f),
      Discounted(name = "Mouse", category = "tech", price = 25.50, discount = 0.2f),
      Regular(name = "dress", category = "clothes", price = 49.90)
    )
    val filteredList: List[Article] = List(
      Regular(name = "Rice", category = "food", price = 10.0),
      Regular(name = "Biscuits", category = "food", price = 2.0),
      Regular(name = "dress", category = "clothes", price = 49.90)
    )

    assert(regularItems(articles) == filteredList)
  }

  test("applyDiscount on discounted item should return the item with an updated price") {
    val d = Discounted("Carrot", "Food", 1.5, 0.5f)

    assert(applyDiscount(d).price == 0.75)
  }

  test("applyCoupon on a Discounted article of the right category should return a lambda function that applies the coupon") {
    val d = Discounted("Carrot", "Food", 2.0, 0.2f)
    val coupon = applyCoupon(0.5f, "Food")
    assert(coupon(d) == 1.0)
  }

  test("applyCoupon on a Regular article of the right category should return a lambda function that applies the coupon") {
    val r = Regular("Carrot", "Food", 2.0)
    val coupon = applyCoupon(0.5f, "Food")
    assert(coupon(r) == 1.0)
  }

  test("cartAmount on a list of Article should apply discounts and coupons before calculating and returning total price of the cart") {
    val articles: List[Article] = List(
      Regular(name = "Rice", category = "food", price = 10.0),
      Discounted(name = "Chocolate", category = "food", price = 8.0, discount = 0.1f),
      Regular(name = "Biscuits", category = "food", price = 2.0),
      Discounted(name = "Monitor", category = "tech", price = 119.99, discount = 0.1f),
      Discounted(name = "Mouse", category = "tech", price = 25.50, discount = 0.2f),
      Regular(name = "dress", category = "clothes", price = 49.90)
    )
    val coupon = applyCoupon(0.5f, "food")

    assert(~=(cartAmount(articles, coupon), 187.8, 0.1))
  }
}
