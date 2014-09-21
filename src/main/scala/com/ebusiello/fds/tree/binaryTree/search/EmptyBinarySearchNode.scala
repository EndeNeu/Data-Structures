package com.ebusiello.fds.tree.binaryTree.search

import com.ebusiello.fds.tree.binaryTree.balanced.{LeftBalancedEmptyNode, AbstractBalancedBinaryNode}

private[binaryTree] class EmptyBinarySearchNode[T] extends AbstractBinarySearchNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T] =
    new BinarySearchNode[T](mValue, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T])

  override def map[V](f: (T) => V): AbstractBalancedBinaryNode[V] =
    new LeftBalancedEmptyNode[V]

  override def toString =
    "E"

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def leftRelativeDepth: Int =
    0

  override def rightRelativeDepth: Int =
    0

}