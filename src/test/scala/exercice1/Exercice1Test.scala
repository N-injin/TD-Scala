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
}
