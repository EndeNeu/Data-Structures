package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode


final class LeftBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends AbstractBalancedBinaryTree[T](head) {

  override def toRight: RightBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new RightBalancedBinaryTree[T](balancedNode.toRight)
  }

  override def toLeft: LeftBalancedBinaryTree[T] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[T](balancedNode.toLeft)
  }

  override def map[V](f: (T) => V): AbstractBalancedBinaryTree[V] = head match {
    case balancedNode: BalancedBinaryNode[T] => new LeftBalancedBinaryTree[V](balancedNode.map(f))
  }

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBalancedBinaryTree[T] =
    if (isEmpty) new LeftBalancedBinaryTree[T](new LeftBalancedBinaryNode[T](mValue, new EmptyBalancedBinarySearchNode[T, LeftBalancedBinaryNode[T]], new EmptyBalancedBinarySearchNode[T, LeftBalancedBinaryNode[T]]))
    else new LeftBalancedBinaryTree[T](head.insert(mValue))
}

object LeftBalancedBinaryTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: LeftBalancedBinaryTree[T] = new LeftBalancedBinaryTree[T](new EmptyBalancedBinarySearchNode[T, LeftBalancedBinaryNode[T]])
}