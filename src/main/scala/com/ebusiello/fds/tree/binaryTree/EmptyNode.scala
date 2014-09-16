package com.ebusiello.fds.tree.binaryTree

import searchTree.BinaryNode

private[binaryTree] final class EmptyNode[T] extends AbstractBinaryNode[T] {

  override def toString =
    "E"

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def reduceBinaryTree[S](f: (T, T) => T): T = ???

  override def insert(mValue: T)(implicit ord: Ordering[T]): BinaryNode[T] =
    new BinaryNode[T](mValue, new EmptyNode[T], new EmptyNode[T])

  override def map[V](f: T => V): EmptyNode[V] =
    new EmptyNode[V]
}