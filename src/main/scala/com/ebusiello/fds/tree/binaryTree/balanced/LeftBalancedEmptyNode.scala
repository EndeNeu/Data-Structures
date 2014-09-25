package com.ebusiello.fds.tree.binaryTree.balanced

/**
 * Concrete implementation of a left balanced empty node.
 */
class LeftBalancedEmptyNode[T] extends EmptyBalancedBinaryNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBalancedBinaryNode[T] =
    new LeftBalancedBinaryNode[T](mValue, new LeftBalancedEmptyNode[T], new LeftBalancedEmptyNode[T])

  override def map[V, VLR <: AbstractBalancedBinaryNode[V]](f: (T) => V): AbstractBalancedBinaryNode[V] =
    new LeftBalancedEmptyNode[V]

  override def length: Int = 0
}
