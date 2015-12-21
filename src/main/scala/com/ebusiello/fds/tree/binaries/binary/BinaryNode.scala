package com.ebusiello.fds.tree.binaries.binary

import com.ebusiello.fds.tree.binaries.GenericBinaryNode
import com.ebusiello.fds.tree.generic.node.OrderableNode

private[binary] class BinaryNode[T](val value: T, val left: BinaryNode[T], val right: BinaryNode[T]) extends GenericBinaryNode[T, BinaryNode] with OrderableNode[T, BinaryNode] {

  /**
   * Insert can happen between leafs, ordering is preserved
   *
   *
   *    1  insert(4)    1
   *   / \             / \
   *  2  5            2  5
   *    / \          /\  /\
   *   3  4         E E 3 4
   */
  override def insert(newValue: T)(implicit ord: Ordering[T]): BinaryNode[T] =
    if (newValue == value) this // guard against duplicates.
    else if (ord.lt(newValue, value)) new BinaryNode(value, left.insert(newValue), right)
    else new BinaryNode(value, left, right.insert(newValue))

  /**
   * Remove a node from the tree, replace it with the left leaf if non empty, else with the right leaf
   * if not empty, else simpli an empty node.
   *
   *     4  remove 2 -> 4
   *    / \            / \
   *   2  3           0  3
   *  /\             /\
   * 0 1            E 1
   *
   *     4  remove 2 -> 4
   *    / \            / \
   *   2  3           E  3
   *
   */
  override def remove(v: T): BinaryNode[T] =
    if (v == value) {
      // if this node is to be removed and the left leaf is non empty pick that to replace this node
      if (left.nonEmpty) new BinaryNode[T](left.value, new EmptyBinaryNode[T], right)
      // else do the same with the right
      else if (right.nonEmpty) new BinaryNode[T](right.value, left, new EmptyBinaryNode[T])
      // else if both are empty just return an empty node.
      else new EmptyBinaryNode[T]
    } else new BinaryNode[T](value,
      left.remove(v),
      right.remove(v))

  /**
   * Iterate the com.ebusiello.fds.tree and map the function to all of its elements.
   */
  override def map[V](f: (T) => V): BinaryNode[V] =
    new BinaryNode[V](f(value), left.map(f), right.map(f))

  override def isEmpty: Boolean =
    false

  /**
   * Note that finding a value in a balanced com.ebusiello.fds.tree can lead to the full com.ebusiello.fds.tree traversal, that is
   * if the value is found the function will keep traversing the com.ebusiello.fds.tree looking for the value until reaches
   * an empty node.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (value == mValue) true
    else left.find(mValue) || right.find(mValue)

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)

  override def stringify: String = (left, right) match {
    case (l: BinaryNode[T], r: BinaryNode[T]) =>
      s"${l.stringify}--$value--${r.stringify}"
  }

  /**
   * calculate depth of the tree
   */
  override def depth: Int = {
    val ld = left.depth
    val rd = right.depth
    if (ld > rd) 1 + ld
    else 1 + rd
  }

  override def length: Int =
    1 + left.length + right.length

}