package com.ebusiello.fds.tree.binaryTree.binary

class BinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  override def insert(mValue: T, where: T): AbstractBinaryNode[T] =
    if (where == value) new BinaryNode[T](mValue, new BinaryNode[T](value, left, new EmptyBinaryNode[T]), right)
    else new BinaryNode[T](mValue, left.insert(mValue, where), right.insert(mValue, where))

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new BinaryNode[V](f(value), left.map(f), right.map(f))

  override def isEmpty: Boolean =
    false

  /**
   * Find a value in a tree.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (value == mValue) true
    else left.find(mValue) || right.find(mValue)

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)

  override def leftRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + right.rightRelativeDepth
}

private[binaryTree] object BinaryNode {
  def unapply[T](t: BinaryNode[T]) =
    Option(t.value, t.left, t.right)
}
