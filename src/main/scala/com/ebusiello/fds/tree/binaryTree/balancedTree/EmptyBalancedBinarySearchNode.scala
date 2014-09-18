package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.searchTree.BinarySearchNode
import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, EmptyNode}

private[binaryTree] final class EmptyBalancedBinarySearchNode[T] extends EmptyNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    new BinarySearchNode[T](mValue, new EmptyBalancedBinarySearchNode[T], new EmptyBalancedBinarySearchNode[T])

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new EmptyBalancedBinarySearchNode[V]

}