package com.ebusiello.fds.tree.binaries.avl.generic

class EmptyAVLNode[T] extends AVLNode[T](null.asInstanceOf[T], null, null) {

  override def map[V](f: (T) => V): EmptyAVLNode[V] =
    new EmptyAVLNode[V]

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def insert(newValue: T)(implicit ord: Ordering[T]): AVLNode[T] =
    new AVLNode[T](newValue, new EmptyAVLNode[T], new EmptyAVLNode[T])

  override def remove(v: T): AVLNode[T] =
    this

  override def nonEmpty: Boolean =
    !isEmpty

  override def stringify: String = "E"

  override def length: Int = 0

  override def depth: Int = 0

  override protected def rotateNode(): AVLNode[T] = this

  override def relativeDepth: Int = 0

  override def rebalance()(implicit ord: Ordering[T]): AVLNode[T] = this

  override def resort()(implicit ord: Ordering[T]): AVLNode[T] = this
}
