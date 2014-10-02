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
   * 1  -- insert(4) -->  1
   * / \                  / \
   * 2  3                 2  3
   * / \
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

  /**
   * Delete a node from a tree, this function may not produce the optima tree after delete:
   *
   * Delete 10:
   *
   *          1              1
   *        /  \           /  \
   *      -10  10        -10  7
   *          /  \           / \
   *         6   15         6  15
   *          \              \
   *          7              8
   *           \
   *           8
   *
   *
   *          1              1
   *        /  \           /  \
   *      -10  10        -10  9
   *          /  \           / \
   *         6   15         6  15
   *          \
   *          9
   *
   *          1              1
   *        /  \           /  \
   *      -10  10        -10  9
   *          /  \           / \
   *         9   15         8  15
   *        /              /
   *       8              7
   *      /
   *     7
   *
   *          1              1
   *        /  \           /  \
   *      -10  10        -10  8
   *          /  \           / \
   *         8   15         7  15
   *        / \              \
   *       7   9             9
   *
   */
  override def delete(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T] =
    if (mValue == value) this match {
      // if it's a node without non empty nodes
      case BinarySearchNode(v, l: EmptyBinarySearchNode[T], r: EmptyBinarySearchNode[T]) =>
        new EmptyBinarySearchNode[T]
      // if it's a node with only a left leaf
      case BinarySearchNode(v, l: BinarySearchNode[T], r: EmptyBinarySearchNode[T]) =>
        l
      // if it's a node with only a right leaf
      case BinarySearchNode(v, l: EmptyBinarySearchNode[T], r: BinarySearchNode[T]) =>
        r
      // if it has both leafs, find the minimum value in the tree using the left leaf
      // and change this with that value, then delete the leaf we substituted this to
      // which of course will be one of the lowest leafs.
      case BinarySearchNode(v, l: BinarySearchNode[T], r: BinarySearchNode[T]) =>
        val minimum = l.findLeftMin(v)
        new BinarySearchNode(minimum, left.delete(minimum), right)
    }
    else new BinarySearchNode[T](value, left.delete(mValue), right.delete(mValue))

  /**
   * Helper function to used to substitute the deleted node.
   */
  private[search] def findLeftMin(currentMin: T)(implicit ord: Ordering[T]): T = this match {
    case BinarySearchNode(v, l: BinarySearchNode[T], r: BinarySearchNode[T]) =>
      import ord.mkOrderingOps
      if (value < currentMin) r.findLeftMin(value)
      else r.findLeftMin(currentMin)
    // if we have only a right node return that value.
    case BinarySearchNode(v, l: EmptyBinarySearchNode[T], r: BinarySearchNode[T]) =>
      r.value
    case BinarySearchNode(v, l: BinarySearchNode[T], r: EmptyBinarySearchNode[T]) =>
      import ord.mkOrderingOps
      if (value < currentMin) l.findLeftMin(value)
      else l.findLeftMin(currentMin)
    case BinarySearchNode(v, l: EmptyBinarySearchNode[T], r: EmptyBinarySearchNode[T]) =>
      import ord.mkOrderingOps
      if (value < currentMin) value
      else currentMin

  }
}

private[binaryTree] object BinarySearchNode {

  def unapply[T](t: BinarySearchNode[T]) =
    Option(t.value, t.left, t.right)

}