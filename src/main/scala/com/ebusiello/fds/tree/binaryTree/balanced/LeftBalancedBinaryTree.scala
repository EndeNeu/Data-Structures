package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree._

/**
 * A variation of a AVL tree (Adelson-Velskii and Landis tree http://en.wikipedia.org/wiki/AVL_tree), the balancing has preference towards the left side.
 */
final class LeftBalancedBinaryTree[T](head: AbstractBalancedBinaryNode[T]) extends AbstractBalancedBinaryTree[T, LeftBalancedBinaryTree, LeftBalancedBinaryTree](head) with Sortable[T, LeftBalancedBinaryTree] {

  override def toRight: RightBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new RightBalancedBinaryTree[T](balancedNode.toRight)
  }

  override def toLeft: LeftBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[T](balancedNode.toLeft)
  }

  override def map[V](f: (T) => V): LeftBalancedBinaryTree[V] =
    if (isEmpty) new LeftBalancedBinaryTree[V](new LeftBalancedEmptyNode[V])
    else head match {
      case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[V](balancedNode.map(f))
    }

  override def insert(mValue: T)(implicit ord: Ordering[T]): LeftBalancedBinaryTree[T] =
    if (isEmpty) new LeftBalancedBinaryTree[T](new LeftBalancedBinaryNode[T](mValue, new LeftBalancedEmptyNode[T], new LeftBalancedEmptyNode[T]))
    else new LeftBalancedBinaryTree[T](head.insert(mValue))

  override def sort(direction: Direction)(implicit ord: Ordering[T]): LeftBalancedBinaryTree[T] = {
    val folded = foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)
    direction match {
      case DESC => LeftBalancedBinaryTree.fromColl(folded.sorted(ord.reverse))
      case ASC => LeftBalancedBinaryTree.fromColl(folded.sorted)
    }
  }
}

object LeftBalancedBinaryTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): LeftBalancedBinaryTree[T] =
    coll.foldLeft(LeftBalancedBinaryTree.emptyTree[T])(_.insert(_))

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: LeftBalancedBinaryTree[T] =
    new LeftBalancedBinaryTree[T](new LeftBalancedEmptyNode[T])

}