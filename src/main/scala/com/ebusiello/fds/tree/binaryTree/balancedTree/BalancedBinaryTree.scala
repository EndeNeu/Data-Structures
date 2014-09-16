package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryTree, AbstractBinaryNode}

class BalancedBinaryTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, BalancedBinaryTree, BalancedBinaryTree](head) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): BalancedBinaryTree[T] = ???

  // TODO map should return a normal binary tree because the ordering clause could not be respected
  override def map[V](f: (T) => V): BalancedBinaryTree[V] = ???

  /**
   * Reduce a tree
   */
  override def reduceBinaryTree[S](f: (T, T) => T): T = ???
}
