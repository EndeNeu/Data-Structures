package com.ebusiello.data.structure.immutable.trees.binaries.binary

private[binary] final class EmptyBinarySearchNode[T] extends BinarySearchNode[T](null.asInstanceOf[T], null, null) {

  override def map[V](f: (T) => V): EmptyBinarySearchNode[V] = new EmptyBinarySearchNode[V]

  override def isEmpty: Boolean = true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = z

  override def insert(newValue: T)(implicit ord: Ordering[T]): BinarySearchNode[T] =
    new BinarySearchNode[T](newValue, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T])

  override def remove(v: T): BinarySearchNode[T] = this

  override def nonEmpty: Boolean = !isEmpty

  override def stringify: String = "E"

  override def length: Int = 0

  override def depth: Int = 0

  override def relativeDepth: Int = 0

  override protected def rotateNode(): BinarySearchNode[T] = this

  override def resort()(implicit ord: Ordering[T]): BinarySearchNode[T] =
    this

}