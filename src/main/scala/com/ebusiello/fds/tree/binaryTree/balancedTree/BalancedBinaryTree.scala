package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, AbstractBinaryTree}

private[binaryTree] abstract class BalancedBinaryTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, BalancedBinaryTree, BalancedBinaryTree](head) {

  def createNode(value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): AbstractBinaryNode[T]

  def createTree(head: AbstractBinaryNode[T]): BalancedBinaryTree[T]

  override def map[V](f: (T) => V): BalancedBinaryTree[V] = ???

  override def insert(mValue: T)(implicit ord: Ordering[T]): BalancedBinaryTree[T] =
    if (isEmpty) createTree(createNode(mValue, new EmptyBalancedBinarySearchNode[T], new EmptyBalancedBinarySearchNode[T]))
    else createTree(head.insert(mValue))

  def toRight: RightBalancedBinaryTree[T]

  def toLeft: LeftBalancedBinaryTree[T]


}

final class LeftBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends BalancedBinaryTree[T](head) {

  override def createNode(value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): AbstractBinaryNode[T] =
    new LeftBalancedBinaryNode[T](value, l, r)

  override def createTree(head: AbstractBinaryNode[T]): BalancedBinaryTree[T] =
    new LeftBalancedBinaryTree[T](head)

  override def toRight: RightBalancedBinaryTree[T] = ???

  override def toLeft: LeftBalancedBinaryTree[T] = ???
}

object LeftBalancedBinaryTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: LeftBalancedBinaryTree[T] = new LeftBalancedBinaryTree[T](new EmptyBalancedBinarySearchNode[T])
}

final class RightBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends BalancedBinaryTree[T](head) {

  override def createNode(value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): AbstractBinaryNode[T] =
    new RightBalancedBinaryNode[T](value, l, r)

  override def createTree(head: AbstractBinaryNode[T]): BalancedBinaryTree[T] =
    new RightBalancedBinaryTree[T](head)

  override def toRight: RightBalancedBinaryTree[T] = ???

  override def toLeft: LeftBalancedBinaryTree[T] = ???
}

object RightBalancedBinaryTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T] = new RightBalancedBinaryTree[T](new EmptyBalancedBinarySearchNode[T])
}