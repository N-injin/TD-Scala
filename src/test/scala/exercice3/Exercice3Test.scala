package exercice3

import exercice3.Exercice3.{NonNul, Nul, Optional}

class Exercice3Test extends org.scalatest.funsuite.AnyFunSuite {
  test("Objects should be created") {
    val nullInt: Optional[Int] = Nul
    val oneInt: Optional[Int] = NonNul(1)
    val nullString: Optional[String] = Nul
    val hello: Optional[String] = NonNul("Hello")
  }

  test("fromNullable on value should create NonNul of given value") {
    val hello: Optional[String] = Optional.fromNullable("Hello")

    assert(hello == NonNul("Hello"))
  }

  test("toList on NonNul shoud return the item in a list of 1 element") {
    val hello: Optional[String] = NonNul("Hello")

    assert(hello.toList == "Hello" :: Nil)
  }

  test("toList on Nul should return Nil") {
    val nulInt: Optional[Int] = Nul

    assert(nulInt.toList == Nil)
  }

  test("orElse on NonNul should return the item") {
    val hello: Optional[String] = NonNul("Hello")

    assert(hello.orElse(NonNul("world")) == NonNul("Hello"))
  }

  test("orElse on Nul should return the alternative item") {
    val nullInt: Optional[Int] = Nul

    assert(nullInt.orElse(NonNul("test")) == NonNul("test"))
  }

  test("getOrElse on NonNul should return item's value") {
    val hello: Optional[String] = NonNul("Hello")

    assert(hello.getOrElse("World") == "Hello")
  }

  test("getOrElse on Nul should return the alternative value") {
    val nullInt: Optional[Int] = Nul

    assert(nullInt.getOrElse(5) == 5)
  }
}
