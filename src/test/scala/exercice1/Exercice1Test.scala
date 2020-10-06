package exercice1

class Exercice1Test extends org.scalatest.funsuite.AnyFunSuite {
  test("myMin should return the minimum in a list") {
    val l1: List[Int] = List(4, 6, 1, 9, 2)

    assert(Exercice1.myMin(l1) === 1)
  }

  test("myMin on empty list should throw an exception") {
    val l1: List[Int] = List()

    assertThrows[IllegalArgumentException] {
      Exercice1.myMin(l1)
    }
  }

  test("egaux on identical lists should return true") {
    assert(Exercice1.egaux(1 :: 2 :: 3 :: Nil, 1 :: 2 :: 3 :: Nil))
  }

  test("egaux on 2 lists of the same size with different values should return false") {
    assert(!Exercice1.egaux(1 :: 2 :: 3 :: Nil, 1 :: 2 :: 4 :: Nil))
  }

  test("egaux on lists of different size should return false") {
    assert(!Exercice1.egaux(1 :: 2 :: 3 :: Nil, 1 :: 2 :: 3 :: 4 :: Nil))
  }
}
