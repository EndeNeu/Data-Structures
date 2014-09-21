package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

/**
 * A right balanced node is one that favours the left branch, the depth is used as a discriminator,
 * if you insert the numbers from 1 to 7 in order you will end up with this tree
 *
 *        1
 *      /  \
 *    2    3
 *   / \  / \
 *  4  5 6  7
 */
private[binaryTree] final class LeftBalancedBinaryNode[T](value: T, left: AbstractBinaryNode[T], right: AbstractBinaryNode[T]) extends BalancedBinaryNode[T](value, left, right) {

  /**
   * To keep the tree balanced on insert we propagate the insert using the relative depth:
   *
   *        1 -- insert 9 -->  1
   *      /  \                /  \
   *    2    3               2   3
   *   / \  / \             / \ / \
   *  4  E E  E            4  9 E E
   *
   * The relative depth of the left branch when the insert is propagated to 1 is 1 because
   * the node with value 2 has an empty node on its right branch, the right 1 is also 1 and at parity
   * the left branch wins. after the insert the left node has depth 2 and the right 1, so the nex insert is propagated
   * to the right branch
   *
   *        1 -- insert 8 -->  1
   *      /  \                /  \
   *    2    3               2   3
   *   / \  / \             / \ / \
   *  4  9 E  E            4  9 8 E
   *
   */
  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case LeftBalancedBinaryNode(v, _, _) if mValue == v => this
    case LeftBalancedBinaryNode(v, l, r) =>
      if (l.leftRelativeDepth <= r.rightRelativeDepth) new LeftBalancedBinaryNode[T](v, l.insert(mValue), r)
      else new LeftBalancedBinaryNode[T](v, l, r.insert(mValue))
  }

  override def isRight: Boolean =
    false

  override def isLeft: Boolean =
    true

  override def map[V, LR <: AbstractBalancedBinaryNode[V]](f: (T) => V): AbstractBinaryNode[V] = this match {
    case LeftBalancedBinaryNode(v, l: LeftBalancedBinaryNode[T], r: LeftBalancedBinaryNode[T]) => new LeftBalancedBinaryNode[V](f(value), l.map(f), r.map(f))
  }

}

private[binaryTree] object LeftBalancedBinaryNode {
  def unapply[T](t: LeftBalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}