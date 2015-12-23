package com.ebusiello.fds.tree.binaries

import com.ebusiello.fds.tree.generic.node._

import scala.language.higherKinds

/**
 * Generalization of a Binary Node.
 *
 * @tparam T the type of the value held by this node.
 * @tparam S the type of node.
 */
private[tree] trait GenericBinaryNode[T, S[T] <: GenericBinaryNode[T, S]] extends Node[T] with FindableNode[T] with StringifiableNode with RemoveableNode[T, S] {

  val left: S[T]

  val right: S[T]

  def map[V](f: T => V): S[V]

  override def isEmpty: Boolean =
    false

  override def leftRelativeDepth: Int =
    (if (left.nonEmpty && right.nonEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.nonEmpty && right.nonEmpty) 1 else 0) + right.rightRelativeDepth

  /**
   * calculate depth of the tree
   */
  def depth: Int = {
    val ld = left.depth
    val rd = right.depth
    if (ld > rd) 1 + ld
    else 1 + rd
  }

  def length: Int =
    1 + left.length + right.length

  override def stringify: String = (left, right) match {
    case (l: GenericBinaryNode[T, S], r: GenericBinaryNode[T, S]) =>
      s"${l.stringify} ~ $value ~ ${r.stringify}"
  }

  /**
   * Note that finding a value in a balanced com.ebusiello.fds.tree can lead to the full com.ebusiello.fds.tree traversal, that is
   * if the value is found the function will keep traversing the com.ebusiello.fds.tree looking for the value until reaches
   * an empty node.
   */
  def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (value == mValue) true
    else left.find(mValue) || right.find(mValue)

  def foldTree[Z](z: Z)(f: (Z, T) => Z)(compose: (Z, Z) => Z): Z =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
}