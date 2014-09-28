package com.ebusiello.fds.tree.binaryTree.search

import com.ebusiello.fds.tree.binaryTree.balanced.{AbstractBalancedBinaryNode, LeftBalancedBinaryNode}

/**
 * Implementation of AbstractBinarySearchNode and AbstractBinaryNode
 */
final class BinarySearchNode[T](val value: T, val left: AbstractBinarySearchNode[T], val right: AbstractBinarySearchNode[T]) extends AbstractBinarySearchNode[T] {

  override def toString =
    left.toString + "--T(" + value.toString + ")--" + right.toString

  override def isEmpty: Boolean =
    false

  /**
   * Find is driven by the ordering, if the value we are looking for is minor than the current value propagate the
   * search to the left branch, else to the right branch.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = {
    if (mValue == value) true
    else {
      import ord.mkOrderingOps
      if (mValue < value) left.find(mValue)
      else right.find(mValue)
    }
  }

  /**
   * Insert is ordered, the left branch must always hold a value minor than the current value
   * and the right branch must always hold a value which is major than the current value
   *
   *     1  -- insert(4) -->  1
   *    / \                  / \
   *   2  3                 2  3
   *  / \
   * E  4
   *
   */
  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T] = this match {
    case BinarySearchNode(_, _, _) if mValue == value => this
    case BinarySearchNode(_, l: AbstractBinarySearchNode[T], r: AbstractBinarySearchNode[T]) =>
      import ord.mkOrderingOps
      if (mValue < value) new BinarySearchNode[T](value, l.insert(mValue), r)
      else new BinarySearchNode[T](value, l, r.insert(mValue))
  }

  override def map[V](f: T => V): AbstractBalancedBinaryNode[V] = this match {
    case BinarySearchNode(_, l: AbstractBinarySearchNode[T], r: AbstractBinarySearchNode[T]) =>
      new LeftBalancedBinaryNode[V](f(value), l.map(f), r.map(f))
  }

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)

  override def leftRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + right.rightRelativeDepth

  override def delete(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T] =
    if (mValue == value) this match {
      case BinarySearchNode(v, l: EmptyBinarySearchNode[T], r: EmptyBinarySearchNode[T]) =>
        new EmptyBinarySearchNode[T]
      case BinarySearchNode(v, l: AbstractBinarySearchNode[T], r: EmptyBinarySearchNode[T]) =>
        l
      case BinarySearchNode(v, l: EmptyBinarySearchNode[T], r: AbstractBinarySearchNode[T]) =>
        r
      case BinarySearchNode(v, l: AbstractBinarySearchNode[T], r: AbstractBinarySearchNode[T]) =>
        val minimum = findLeftMin(v)
        new BinarySearchNode(minimum, left.delete(minimum), right)
    }
    else new BinarySearchNode[T](value, left.delete(mValue), right.delete(mValue))

  override def findLeftMin(currentMin: T)(implicit ord: Ordering[T]): T = {
    import ord.mkOrderingOps
    if (value < currentMin) left.findLeftMin(value)
    else left.findLeftMin(currentMin)
  }
}

private[binaryTree] object BinarySearchNode {

  def unapply[T](t: BinarySearchNode[T]) =
    Option(t.value, t.left, t.right)

}