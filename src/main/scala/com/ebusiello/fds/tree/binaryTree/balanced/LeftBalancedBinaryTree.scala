package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.{ASC, DESC, Direction, SortableTree}
import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

final class LeftBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends AbstractBalancedBinaryTree[T, LeftBalancedBinaryTree, LeftBalancedBinaryTree](head) with SortableTree[T, LeftBalancedBinaryTree] {

  override def toRight: RightBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new RightBalancedBinaryTree[T](balancedNode.toRight)
  }

  override def toLeft: LeftBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[T](balancedNode.toLeft)
  }

  override def map[V](f: (T) => V): LeftBalancedBinaryTree[V] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[V](balancedNode.map(f))
  }

  override def insert(mValue: T)(implicit ord: Ordering[T]): LeftBalancedBinaryTree[T] =
    if (isEmpty) new LeftBalancedBinaryTree[T](new LeftBalancedBinaryNode[T](mValue, new EmptyBalancedBinarySearchNode[T, LeftBalancedBinaryNode[T]], new EmptyBalancedBinarySearchNode[T, LeftBalancedBinaryNode[T]]))
    else new LeftBalancedBinaryTree[T](head.insert(mValue))

  override def sort(direction: Direction)(implicit ord: Ordering[T]): LeftBalancedBinaryTree[T] = {
    val folded = foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)
    direction match {
      case DESC => LeftBalancedBinaryTree.fromColl(folded.sorted)
      case ASC => LeftBalancedBinaryTree.fromColl(folded.sorted(ord.reverse))
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
    new LeftBalancedBinaryTree[T](new EmptyBalancedBinarySearchNode[T, LeftBalancedBinaryNode[T]])

}