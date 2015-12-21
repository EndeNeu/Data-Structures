//package com.ebusiello.fds.tree.binaryTree.balanced.generic
//
//import com.ebusiello.fds.tree.binaryTree.GenericBinaryNode
//import com.ebusiello.fds.tree.binaryTree.balanced.left.LeftBalancedBinaryNode
//import com.ebusiello.fds.tree.binaryTree.balanced.right.RightBalancedBinaryNode
//import scala.language.higherKinds
//
///**
// * Semi concrete implementation of an abstract balanced binary node, generic methods are implemented here,
// * specific ones are implemented in subclasses.
// */
//private[binaryTree] trait GenericBalancedBinaryNode[T, S[_] <: GenericBalancedBinaryNode] extends GenericBinaryNode[T, S] {
//
//  def isRight: Boolean
//
//  def isLeft: Boolean
//
//  /**
//   * Mutate a LeftBalanced node into a right one.
//   */
//  def toRight: RightBalancedBinaryNode[T] = this match {
//    case LeftBalancedBinaryNode(v, l: LeftBalancedBinaryNode[T], r: LeftBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](v, l.toRight, r.toRight)
//    case RightBalancedBinaryNode(v, l: RightBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new RightBalancedBinaryNode[T](v, l.toRight, r.toRight)
//  }
//
//  /**
//   * Mutate a RightBalanced node into a right one.
//   */
//  def toLeft: LeftBalancedBinaryNode[T] = this match {
//    case RightBalancedBinaryNode(_, l: RightBalancedBinaryNode[T], r: RightBalancedBinaryNode[T]) => new LeftBalancedBinaryNode[T](value, l.toLeft, r.toLeft)
//    case lb: LeftBalancedBinaryNode[T] => lb
//  }
//
//  /**
//   * The relative depth is the depth counted from this node and it's incremented only if both
//   * the left and right node are non empty nodes.
//   */
//  override def leftRelativeDepth: Int =
//    (if (left.nonEmpty && right.nonEmpty) 1 else 0) + left.leftRelativeDepth
//
//  /**
//   * The relative depth is the depth counted from this node and it's incremented only if both
//   * the left and right node are non empty nodes.
//   */
//  override def rightRelativeDepth: Int =
//    (if (left.nonEmpty && right.nonEmpty) 1 else 0) + right.rightRelativeDepth
//
//  override def isEmpty: Boolean =
//    false
//
//  /**
//   * Note that finding a value in a balanced com.ebusiello.fds.tree can lead to the full com.ebusiello.fds.tree traversal, that is
//   * if the value is found the function will keep traversing the tree looking for the value until reaches
//   * an empty node.
//   */
//  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
//    if (value == mValue) true
//    else left.find(mValue) || right.find(mValue)
//
//  override def foldTree[V](z: V)(f: (V, T) => V)(compose: (V, V) => V): V =
//    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
//
//  override def length: Int =
//    1 + left.length + right.length
//
//}