package com.ebusiello.fds.tree.binaryTree.random

class EmptyRandomBinaryNode[T] extends AbstractRandomBinaryNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRandomBinaryNode[T] =
    this

  override def map[V](f: (T) => V): AbstractRandomBinaryNode[V] =
    new EmptyRandomBinaryNode[V]

  override def rightRelativeDepth: Int =
    0

  override def leftRelativeDepth: Int =
    0

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z
}
