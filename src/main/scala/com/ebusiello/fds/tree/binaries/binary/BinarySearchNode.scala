package com.ebusiello.fds.tree.binaries.binary

import com.ebusiello.fds.tree.binaries.GenericBinaryNode
import com.ebusiello.fds.tree.generic.node.OrderableNode

private[binary] class BinarySearchNode[T](val value: T, val left: BinarySearchNode[T], val right: BinarySearchNode[T]) extends GenericBinaryNode[T, BinarySearchNode] with OrderableNode[T, BinarySearchNode] {

  /**
   * Insert can happen between leafs, ordering is preserved
   *
   *
   * 1  insert(4)    1
   * / \             / \
   * 2  5            2  5
   * / \          /\  /\
   * 3  4         E E 3 4
   */
  override def insert(newValue: T)(implicit ord: Ordering[T]): BinarySearchNode[T] =
    if (newValue == value) this // guard against duplicates.
    else if (ord.lt(newValue, value)) new BinarySearchNode(value, left.insert(newValue), right)
    else new BinarySearchNode(value, left, right.insert(newValue))

  /**
   * Remove a node from the tree, replace it with the left leaf if non empty, else with the right leaf
   * if not empty, else simpli an empty node.
   *
   * https://en.wikibooks.org/wiki/Data_Structures/Trees#Deleting_an_item_from_a_binary_search_tree
   *
   */
  override def remove(v: T): BinarySearchNode[T] =
    if (v == value) {
      // if this node is to be removed and the left leaf is non empty pick that to replace this node
      if (left.nonEmpty && right.isEmpty) new BinarySearchNode[T](left.value, new EmptyBinarySearchNode[T], right)
      // else do the same with the right
      else if (right.nonEmpty && left.isEmpty) new BinarySearchNode[T](right.value, left, new EmptyBinarySearchNode[T])
      // else if both are empty just return an empty node.
      else if (right.isEmpty && left.isEmpty) new EmptyBinarySearchNode[T]
      // if both are not empty, look for the first right node with a left empty node
      // and rotate the tree, after that delete the value who has rotated.
      else rotateTree()
    } else new BinarySearchNode[T](value, left.remove(v), right.remove(v))

  /**
    * Find the leftmost node with an empty left leaf.
    */
  private def findNoLeftNode: BinarySearchNode[T] = {
    if (left.isEmpty) this
    else left.findNoLeftNode
  }

  /**
    * Rotate a tree starting from the right node.
    * https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeletenotrightchildexample.jpg
    */
  private def rotateTree() = {
    val newVal = right.findNoLeftNode.value
    new BinarySearchNode[T](newVal, left, right.remove(newVal))
  }

  override def map[V](f: (T) => V): BinarySearchNode[V] =
    new BinarySearchNode[V](f(value), left.map(f), right.map(f))

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
    case (l: BinarySearchNode[T], r: BinarySearchNode[T]) =>
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