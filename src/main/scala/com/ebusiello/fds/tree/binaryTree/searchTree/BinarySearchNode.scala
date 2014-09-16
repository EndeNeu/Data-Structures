package com.ebusiello.fds.tree.binaryTree.searchTree

private[binaryTree] final class BinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  override def toString =
    left.toString + "--T(" + value.toString + ")--" + right.toString

  override def isEmpty: Boolean =
    false

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = {
    if (mValue == value) true
    else {
      import ord.mkOrderingOps
      if (mValue < value) left.find(mValue)
      else right.find(mValue)
    }
  }

  override def insert(mValue: T)(implicit ord: Ordering[T]): BinaryNode[T] = this match {
    case BinaryNode(_, _, _) if mValue == value => this
    case BinaryNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      import ord.mkOrderingOps
      if (mValue < value) new BinaryNode(value, l.insert(mValue), r)
      else new BinaryNode(value, l, r.insert(mValue))
  }

  override def map[S](f: T => S): BinaryNode[S] = this match {
    case BinaryNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      new BinaryNode[S](f(value), l.map(f), r.map(f))
  }

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = {
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
  }

  override def reduceBinaryTree[S](f: (T, T) => T): T = ???
}

private[binaryTree] object BinaryNode {

  def apply[T](value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): BinaryNode[T] =
    new BinaryNode[T](value, l, r)

  def unapply[T](t: BinaryNode[T]) =
    Option(t.value, t.left, t.right)

}
