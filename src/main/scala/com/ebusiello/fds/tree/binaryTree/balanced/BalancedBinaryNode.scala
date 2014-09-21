package com.ebusiello.fds.tree.binaryTree.balanced

/**
 * Semi concrete implementation of an abstract balanced binary node, generic methods are implemented here,
 * specific ones are implemented in subclasses.
 */
private[binaryTree] abstract class BalancedBinaryNode[T](val value: T, val left: AbstractBalancedBinaryNode[T], val right: AbstractBalancedBinaryNode[T]) extends AbstractBalancedBinaryNode[T] {

  def isRight: Boolean

  def isLeft: Boolean

  /**
   * Mutate a LeftBalanced node into a right one.
   */
  override def toRight: BalancedBinaryNode[T] = this match {
    case LeftBalancedBinaryNode(v, l: LeftBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](v, l.toRight, r.toRight)
    case RightBalancedBinaryNode(v, l: LeftBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](v, l.toRight, r.toRight)
  }

  /**
   * Mutate a RightBalanced node into a right one.
   */
  override def toLeft: BalancedBinaryNode[T] = this match {
    case RightBalancedBinaryNode(_, l: AbstractBalancedBinaryNode[T], r: AbstractBalancedBinaryNode[T]) => new LeftBalancedBinaryNode[T](value, l.toLeft, r.toLeft)
    case LeftBalancedBinaryNode(v, l: LeftBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](v, l.toRight, r.toRight)
  }

  /**
   * The relative depth is the depth counted from this node and it's incremented only if both
   * the left and right node are non empty nodes.
   */
  override def leftRelativeDepth: Int =
    (if (left.nonEmpty && right.nonEmpty) 1 else 0) + left.leftRelativeDepth

  /**
   * The relative depth is the depth counted from this node and it's incremented only if both
   * the left and right node are non empty nodes.
   */
  override def rightRelativeDepth: Int =
    (if (left.nonEmpty && right.nonEmpty) 1 else 0) + right.rightRelativeDepth

  override def isEmpty: Boolean =
    false

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (value == mValue) true
    else left.find(mValue) || right.find(mValue)

  override def foldTree[V](z: V)(f: (V, T) => V)(compose: (V, V) => V): V =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)

}

private[binaryTree] object BalancedBinaryNode {
  def unapply[T](t: BalancedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}