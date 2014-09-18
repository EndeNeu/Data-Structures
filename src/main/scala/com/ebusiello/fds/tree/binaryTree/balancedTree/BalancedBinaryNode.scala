package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode
import scala.language.implicitConversions

private[binaryTree] abstract class BalancedBinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  def isRight: Boolean

  def isLeft: Boolean

  // TODO this is shit should be generalized, the problem is how to call toRight.
  def toRight: RightBalancedBinaryNode[T] = this match {
    case LeftBalancedBinaryNode(_, l: LeftBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](value, l.toRight, r.toRight)
    case LeftBalancedBinaryNode(_, l: EmptyBalancedBinarySearchNode[T], r: RightBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](value, l, r.toRight)
    case LeftBalancedBinaryNode(_, l: LeftBalancedBinaryNode[T], r: EmptyBalancedBinarySearchNode[T]) => new RightBalancedBinaryNode[T](value, l.toRight, r)
    case LeftBalancedBinaryNode(_, l: EmptyBalancedBinarySearchNode[T], r: EmptyBalancedBinarySearchNode[T]) => new RightBalancedBinaryNode[T](value, l, r)
    case _ => throw new Exception("")
  }

  // TODO this is shit should be generalized, the problem is how to call toLeft.
  def toLeft: LeftBalancedBinaryNode[T] = this match {
    case LeftBalancedBinaryNode(_, l: LeftBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new LeftBalancedBinaryNode[T](value, l.toLeft, r.toLeft)
    case LeftBalancedBinaryNode(_, l: EmptyBalancedBinarySearchNode[T], r: RightBalancedBinaryNode[T]) => new LeftBalancedBinaryNode[T](value, l, r.toLeft)
    case LeftBalancedBinaryNode(_, l: LeftBalancedBinaryNode[T], r: EmptyBalancedBinarySearchNode[T]) => new LeftBalancedBinaryNode[T](value, l.toLeft, r)
    case LeftBalancedBinaryNode(_, l: EmptyBalancedBinarySearchNode[T], r: EmptyBalancedBinarySearchNode[T]) => new LeftBalancedBinaryNode[T](value, l, r)
    case _ => throw new Exception("")
  }

  override def leftRelativeDepth: Int =
    (if (left.isEmpty && right.isEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.isEmpty && right.isEmpty) 1 else 0) + right.rightRelativeDepth

  override def map[V](f: (T) => V): AbstractBinaryNode[V] = ???

  /**
   * if a tree/node is empty or not.
   */
  override def isEmpty: Boolean =
    false

  /**
   * Find a value in a tree.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = ???

  /**
   * Fold a tree.
   */
  override def foldTree[V](z: V)(f: (V, T) => V)(compose: (V, V) => V): V = ???

}

private[binaryTree] object BalancedBinaryNode {
  def unapply[T](t: BalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}

private[binaryTree] final class LeftBalancedBinaryNode[T](value: T, left: AbstractBinaryNode[T], right: AbstractBinaryNode[T]) extends BalancedBinaryNode[T](value, left, right) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case LeftBalancedBinaryNode(_, _, _) if mValue == value => this
    case LeftBalancedBinaryNode(v, l, r) =>
      if (l.leftRelativeDepth <= r.rightRelativeDepth) new LeftBalancedBinaryNode[T](v, l.insert(mValue), r)
      else new LeftBalancedBinaryNode[T](v, l, r.insert(mValue))
  }

  override def isRight: Boolean =
    false

  override def isLeft: Boolean =
    true
}

private[binaryTree] object LeftBalancedBinaryNode {
  def unapply[T](t: LeftBalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}

private[binaryTree] final class RightBalancedBinaryNode[T](value: T, left: AbstractBinaryNode[T], right: AbstractBinaryNode[T]) extends BalancedBinaryNode[T](value, left, right) {
  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case RightBalancedBinaryNode(_, _, _) if mValue == value => this
    case RightBalancedBinaryNode(v, l, r) =>
      if (l.leftRelativeDepth > r.rightRelativeDepth) new RightBalancedBinaryNode[T](v, l, r.insert(mValue))
      else new RightBalancedBinaryNode[T](v, l.insert(mValue), r)
  }

  override def isRight: Boolean =
    true

  override def isLeft: Boolean =
    false
}

private[binaryTree] object RightBalancedBinaryNode {
  def unapply[T](t: RightBalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}