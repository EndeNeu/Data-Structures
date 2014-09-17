package com.ebusiello.fds.tree.binaryTree.searchTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

private[binaryTree] final class BinarySearchNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  override def leftRelativeDepth: Int =
    1 + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    1 + rightRelativeDepth

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

  override def insert(mValue: T)(implicit ord: Ordering[T]): BinarySearchNode[T] = this match {
    case BinarySearchNode(_, _, _) if mValue == value => this
    case BinarySearchNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      import ord.mkOrderingOps
      if (mValue < value) new BinarySearchNode(value, l.insert(mValue), r)
      else new BinarySearchNode(value, l, r.insert(mValue))
  }

  override def map[S](f: T => S): BinarySearchNode[S] = this match {
    case BinarySearchNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      new BinarySearchNode[S](f(value), l.map(f), r.map(f))
  }

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = {
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
  }

}

private[binaryTree] object BinarySearchNode {

  def apply[T](value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): BinarySearchNode[T] =
    new BinarySearchNode[T](value, l, r)

  def unapply[T](t: BinarySearchNode[T]) =
    Option(t.value, t.left, t.right)

}
