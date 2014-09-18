package com.ebusiello.fds.tree.binaryTree.searchTree

import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, EmptyNode}

private[binaryTree] final class EmptyBinarySearchNode[T] extends EmptyNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    new BinarySearchNode[T](mValue, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T])

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new EmptyBinarySearchNode[V]

}