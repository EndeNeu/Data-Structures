package com.ebusiello.fds.tree.binaryTree.searchTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

private[binaryTree] final class EmptyBinarySearchNode[T] extends AbstractBinarySearchNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    new BinarySearchNode[T](mValue, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T])

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new EmptyBinarySearchNode[V]

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
}