package com.ebusiello.fds.tree.binaries.binary

private[binary] final class EmptyBinaryNode[T] extends BinaryNode[T](null.asInstanceOf[T], null, null) {

  override def map[V](f: (T) => V): EmptyBinaryNode[V] =
    new EmptyBinaryNode[V]

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def insert(newValue: T)(implicit ord: Ordering[T]): BinaryNode[T] =
    new BinaryNode[T](newValue, new EmptyBinaryNode[T], new EmptyBinaryNode[T])

  override def remove(v: T): BinaryNode[T] =
    this

  override def nonEmpty: Boolean =
    !isEmpty

  override def stringify: String = "E"

  override def length: Int = 0

  override def depth: Int = 0
}