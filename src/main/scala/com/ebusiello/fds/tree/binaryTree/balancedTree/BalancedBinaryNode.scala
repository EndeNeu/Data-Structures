package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

private[binaryTree] abstract class BalancedBinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  override def leftRelativeDepth: Int =
    1 + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    1 + rightRelativeDepth

  override def map[V](f: (T) => V): AbstractBinaryNode[V] = ???

  /**
   * if a tree/node is empty or not.
   */
  override def isEmpty: Boolean = ???

  /**
   * Find a value in a tree.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = ???

  /**
   * Fold a tree.
   */
  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = ???


}

private[binaryTree] object BalancedBinaryNode {

  def unapply[T](t: BalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)

}

private[binaryTree] final class LeftBalancedBinaryNode[T](value: T, left: AbstractBinaryNode[T], right: AbstractBinaryNode[T]) extends BalancedBinaryNode[T](value, left, right) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case BalancedBinaryNode(_, _, _) if mValue == value => this
    case BalancedBinaryNode(v, l, r) =>
      if (l.leftRelativeDepth <= r.rightRelativeDepth) new LeftBalancedBinaryNode[T](v, l.insert(mValue), r)
      else new LeftBalancedBinaryNode[T](v, l, r.insert(mValue))
  }
}

private[binaryTree] object LeftBalancedBinaryNode {

  def unapply[T](t: LeftBalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)

}

private[binaryTree] final class RightBalancedBinaryNode[T](value: T, left: AbstractBinaryNode[T], right: AbstractBinaryNode[T]) extends BalancedBinaryNode[T](value, left, right) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case BalancedBinaryNode(_, _, _) if mValue == value => this
    case BalancedBinaryNode(v, l, r) =>
      if (l.leftRelativeDepth <= r.rightRelativeDepth) new LeftBalancedBinaryNode[T](v, l.insert(mValue), r)
      else new LeftBalancedBinaryNode[T](v, l, r.insert(mValue))
  }
}

private[binaryTree] object RightBalancedBinaryNode {

  def unapply[T](t: RightBalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)

}