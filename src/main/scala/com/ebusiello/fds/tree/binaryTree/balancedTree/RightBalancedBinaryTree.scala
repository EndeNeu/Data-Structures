package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode


final class RightBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends AbstractBalancedBinaryTree[T](head) {

  override def toRight: RightBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new RightBalancedBinaryTree[T](balancedNode.toRight)
  }

  override def toLeft: LeftBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[T](balancedNode.toLeft)
  }

  override def map[V](f: (T) => V): AbstractBalancedBinaryTree[V] =
    new RightBalancedBinaryTree[V](head.map(f))

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBalancedBinaryTree[T] =
    if (isEmpty) new RightBalancedBinaryTree[T](new RightBalancedBinaryNode[T](mValue, new EmptyBalancedBinarySearchNode[T, RightBalancedBinaryNode[T]], new EmptyBalancedBinarySearchNode[T, RightBalancedBinaryNode[T]]))
    else new RightBalancedBinaryTree[T](head.insert(mValue))
}

object RightBalancedBinaryTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T] = new RightBalancedBinaryTree[T](new EmptyBalancedBinarySearchNode[T, RightBalancedBinaryNode[T]])
}