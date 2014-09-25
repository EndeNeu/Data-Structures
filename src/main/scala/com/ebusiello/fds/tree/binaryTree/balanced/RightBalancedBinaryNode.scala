package com.ebusiello.fds.tree.binaryTree.balanced

import scala.language.implicitConversions

/**
 * A right balanced node is one that favours the right branch, the depth is used as a discriminator,
 * if you insert the numbers from 1 to 7 in order you will end up with this tree
 *
 *        1
 *      /  \
 *    3    2
 *   / \  / \
 *  7  6 5  4
 */
final class RightBalancedBinaryNode[T](value: T, left: AbstractBalancedBinaryNode[T], right: AbstractBalancedBinaryNode[T]) extends BalancedBinaryNode[T](value, left, right) {

  /**
   * To keep the tree balanced on insert we propagate the insert using the relative depth:
   *
   *        1 -- insert 9 -->  1
   *      /  \                /  \
   *    2    3               2   3
   *   / \  / \             / \ / \
   *  E  E E  4            E  E 9 4
   *
   * The relative depth of the right branch when the insert is propagated to the head is 1 because
   * the node with value 3 has an empty node on its left branch, the left one has also depth 1 and at parity
   * the right branch wins. after the insert the right node has depth 2 and the left 1, so the nex insert is propagated
   * to the left branch
   *
   *        1 -- insert 8 -->  1
   *      /  \                /  \
   *    2    3               2   3
   *   / \  / \             / \ / \
   *  4  9 E  E            E  8 9 4
   *
   */
  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBalancedBinaryNode[T] = this match {
    case RightBalancedBinaryNode(v, _, _) if mValue == v => this
    case RightBalancedBinaryNode(v, l: RightBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) =>
      if (r.rightRelativeDepth <= l.leftRelativeDepth) new RightBalancedBinaryNode[T](v, l, r.insert(mValue))
      else new RightBalancedBinaryNode[T](v, l.insert(mValue), r)

    case RightBalancedBinaryNode(v, l: RightBalancedEmptyNode[T], r: RightBalancedBinaryNode[T]) =>
      new RightBalancedBinaryNode[T](v, l.insert(mValue), r)

    case RightBalancedBinaryNode(v, l: RightBalancedEmptyNode[T], r: RightBalancedEmptyNode[T]) =>
      new RightBalancedBinaryNode[T](v, l, r.insert(mValue))

  }

  override def isRight: Boolean =
    true

  override def isLeft: Boolean =
    false

  override def map[V, LR <: AbstractBalancedBinaryNode[V]](f: (T) => V): AbstractBalancedBinaryNode[V] = this match {
    case RightBalancedBinaryNode(v, l: AbstractBalancedBinaryNode[T], r: AbstractBalancedBinaryNode[T]) => new RightBalancedBinaryNode[V](f(value), l.map(f), r.map(f))
  }
}

private[binaryTree] object RightBalancedBinaryNode {
  def unapply[T](t: RightBalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}