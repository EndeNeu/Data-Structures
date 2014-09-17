package com.ebusiello.fds.tree.binaryTree

import searchTree.BinarySearchNode

private[binaryTree] final class EmptyNode[T] extends AbstractBinaryNode[T] {

  override def leftRelativeDepth: Int =
    0

  override def rightRelativeDepth: Int =
    0

  override def toString =
    "E"

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    new BinarySearchNode[T](mValue, new EmptyNode[T], new EmptyNode[T])

  override def map[V](f: T => V): EmptyNode[V] =
    new EmptyNode[V]


}