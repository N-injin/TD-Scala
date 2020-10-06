package exercice4

object Exercice4 {
  sealed abstract class Tree {
    def length: Int

    def isSame(other: Tree): Boolean

    def mirror: Tree
  }

  case class Node(left: Tree, right: Tree) extends Tree {
    override def length: Int = this.left.length + this.right.length

    override def isSame(other: Tree): Boolean = other match {
      case item: Node => this.left.isSame(item.left) && this.right.isSame(item.right)
      case _: Leaf => false
    }

    override def mirror: Tree = this.copy(left = this.right.mirror, right = this.left.mirror)
  }

  case class Leaf(element: Int) extends Tree {
    override def length: Int = 1

    override def isSame(other: Tree): Boolean = other match {
      case item: Leaf => this.element == item.element
      case _: Node => false
    }

    override def mirror: Tree = this
  }
}
