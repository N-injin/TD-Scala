package exercice4

import exercice4.Exercice4.{Leaf, Node}

class Exercice4Test extends org.scalatest.funsuite.AnyFunSuite {
  test("Length should count the amount of leaves") {
    val t1 = Leaf(1)
    val t2 = Node(Leaf(1), Leaf(2))
    val t3 = Node(Leaf(1), Node(Leaf(2), Leaf(3)))

    assert(t1.length == 1)
    assert(t2.length == 2)
    assert(t3.length == 3)
  }

  test("isSame on identical trees should return true") {
    val t1 = Node(Leaf(1), Node(Leaf(2), Leaf(3)))
    val t2 = Node(Leaf(1), Node(Leaf(2), Leaf(3)))

    assert(t1.isSame(t2))
  }

  test("isSame on trees of diferent length should return false") {
    val t1 = Node(Leaf(1), Leaf(2))
    val t2 = Leaf(3)

    assert(!t1.isSame(t2))
  }

  test("isSame on trees with different leaf values should return false") {
    val t1 = Node(Leaf(1), Node(Leaf(2), Leaf(3)))
    val t2 = Node(Leaf(1), Node(Leaf(3), Leaf(2)))

    assert(!t1.isSame(t2))
  }

  test("mirror on a single leaf should return the leaf") {
    val t1 = Leaf(1)

    assert(t1.mirror == Leaf(1))
  }

  test("mirror on a Node should reverse the leaves") {
    val t1 = Node(Leaf(1), Leaf(2))
    val t2 = Node(Leaf(2), Leaf(1))

    assert(t1.mirror == t2)
  }

  test("mirror on a bigger tree should reverse the nodes and leaves") {
    val t1 = Node(Leaf(1), Node(Leaf(2), Leaf(3)))
    val t2 = Node(Node(Leaf(3), Leaf(2)), Leaf(1))

    assert(t1.mirror == t2)
  }
}
