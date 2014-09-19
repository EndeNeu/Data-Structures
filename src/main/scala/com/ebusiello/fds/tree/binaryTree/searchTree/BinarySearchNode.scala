package com.ebusiello.fds.tree.binaryTree.searchTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

/**
 * This class is needed to have a common supertype with a map method inside for both binary search nodes
 * and binary empty search nodes.
 */
private[binaryTree] abstract class AbstractBinarySearchNode[T] extends AbstractBinaryNode[T] {
  def map[V](f: T => V): AbstractBinaryNode[V]
}

/**
 * Implementation of AbstractBinarySearchNode and AbstractBinaryNode
 */
private[binaryTree] final class BinarySearchNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinarySearchNode[T] {

  override def toString =
    left.toString + "--T(" + value.toString + ")--" + right.toString

  override def isEmpty: Boolean =
    false

  override def leftRelativeDepth: Int =
    (if (left.isEmpty && right.isEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.isEmpty && right.isEmpty) 1 else 0) + right.rightRelativeDepth

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = {
    if (mValue == value) true
    else {
      import ord.mkOrderingOps
      if (mValue < value) left.find(mValue)
      else right.find(mValue)
    }
  }

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case BinarySearchNode(_, _, _) if mValue == value => this
    case BinarySearchNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      import ord.mkOrderingOps
      if (mValue < value) new BinarySearchNode[T](value, l.insert(mValue), r)
      else new BinarySearchNode[T](value, l, r.insert(mValue))
  }

  override def map[V](f: T => V): AbstractBinaryNode[V] = this match {
    case BinarySearchNode(_, l: BinarySearchNode[T], r: BinarySearchNode[T]) =>
      new BinarySearchNode[V](f(value), l.map(f), r.map(f))
  }

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = {
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
  }

}

private[binaryTree] object BinarySearchNode {

  def unapply[T](t: BinarySearchNode[T]) =
    Option(t.value, t.left, t.right)

}
