package exercice5
import Exercice5.{Liste, NonVide, Vide}

class Exercice5Test extends org.scalatest.funsuite.AnyFunSuite {
  test("apply should generate a Liste") {
    val list = Liste[Int](1, 2, 3)

    assert(list == NonVide(1, NonVide(2, NonVide(3, Vide))))
  }

  test("concat on 2 lists should concatenate the values") {
    val list1 = Liste[Int](1, 2, 3)
    val list2 = Liste[Int](4, 5, 6)

    assert(list1.concat(list2) == Liste[Int](1, 2, 3, 4, 5, 6))
  }

  test("concat on empty list should return the second list") {
    val list1 = Liste[Int](1, 2, 3)

    assert(Vide.concat(list1) == list1)
  }

  test("map on Liste should apply the lambda to every element") {
    val list = Liste[Int](1, 2, 3, 4)

    assert(list.map(x => x + 1) == Liste[Int](2, 3, 4, 5))
  }

  test("flatMap on Liste should apply the lambda to every element and concatenate every result") {
    val list = Liste[Int](1, 2)

    assert(list.flatMap(x => Liste[Int](x, x + 1)) == Liste[Int](1, 2, 2, 3))
  }

  test("fold on Liste should act like a reduce function (Yes I am too lazy to actually write this last test)") {
    val list = Liste[Int](1, 3, 4, 5)

    assert(list.fold(0, (a, b) => a + b) == 13)
  }
}
