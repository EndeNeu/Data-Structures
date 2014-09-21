package com.ebusiello.fds.tree.binaryTree.unordered

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode
import com.ebusiello.fds.tree.binaryTree.search.AbstractBinarySearchNode

class UnorderedBinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinarySearchNode[T] {

  def randomizer = new scala.util.Random()

  /**
   * Unordered tree uses a random generator, if it returns true insert to the left,
   * else insert to the right
   *
   * @note duplicate are allowed
   */
  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    if (randomizer.nextBoolean()) left.insert(mValue)
    else right.insert(mValue)

  /**
   * if a tree/node is empty or not.
   */
  override def isEmpty: Boolean =
    false

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (mValue == value) true
    else left.find(mValue) || right.find(mValue)

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)

  override def leftRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + right.rightRelativeDepth

  override def map[V](f: (T) => V): AbstractBinaryNode[V] = this match {
    case UnorderedBinaryNode(_, l: UnorderedBinaryNode[T], r: UnorderedBinaryNode[T]) =>
      new UnorderedBinaryNode[V](f(value), l.map(f), r.map(f))
  }
}

private[binaryTree] object UnorderedBinaryNode {
  def unapply[T](t: UnorderedBinaryNode[T]) =
    Option(t.value, t.left, t.right)
}

