package com.ebusiello.fds.tree.binaryTree.binary

class EmptyBinaryNode[T] extends AbstractBinaryNode[T] {

  override def insert(mValue: T, after: T): AbstractBinaryNode[T] =
    this

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new EmptyBinaryNode[V]

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
