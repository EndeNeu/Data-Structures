package com.ebusiello.fds.tree.binaries.binary

import com.ebusiello.fds.tree.binaries.GenericBinaryNode
import com.ebusiello.fds.tree.generic.node.{ BalanceableNode, OrderableNode, RotableNode }

private[binaries] class BinarySearchNode[T](val value: T, val left: BinarySearchNode[T], val right: BinarySearchNode[T]) extends GenericBinaryNode[T, BinarySearchNode] with OrderableNode[T, BinarySearchNode] with RotableNode[T, BinarySearchNode] with BalanceableNode[T, BinarySearchNode] {

  override def leftRelativeDepth: Int = 1 + left.leftRelativeDepth

  override def rightRelativeDepth: Int = 1 + right.leftRelativeDepth

  /**
   * Insert can happen between leafs, ordering is preserved
   *
   *
   * 1  insert(4)    1
   * \             / \
   * 2 5            2  5
   * /           /\  /\
   * 3           E E 3 4
   */
  override def insert(newValue: T)(implicit ord: Ordering[T]): BinarySearchNode[T] =
    if (newValue == value) this // guard against duplicates.
    else if (ord.lt(newValue, value)) new BinarySearchNode(value, left.insert(newValue), right)
    else new BinarySearchNode(value, left, right.insert(newValue))

  /**
   * Remov a node from the tree, replace it with the left leaf if non empty, else with the right leaf
   * if notempty, else simpli an empty node.
   *
   * htps://n.wikibooks.org/wiki/Data_Structures/Trees#Deleting_an_item_from_a_binary_search_tree
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
      else rotateNode()
    } else new BinarySearchNode[T](value, left.remove(v), right.remove(v))

  /**
   * Find the letmost node with an empty left leaf.
   */
  private def findNoLeftNode: BinarySearchNode[T] = {
    if (left.isEmpty) this
    else left.findNoLeftNode
  }

  /**
   * Rotate a treestarting from the right node.
   * https://en.wikbooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeletenotrightchildexample.jpg
   */
  override protected def rotateNode(): BinarySearchNode[T] = {
    val newVal = right.findNoLeftNode.value
    new BinarySearchNode[T](newVal, left, right.remove(newVal))
  }

  override def map[V](f: (T) => V): BinarySearchNode[V] =
    new BinarySearchNode[V](f(value), left.map(f), right.map(f))

  override def rebalance()(implicit ord: Ordering[T]): BinarySearchNode[T] = {
    /*def iterate(previousNode: BinarySearchNode[T]): BinarySearchNode[T] = {
      if (left.nonEmpty && left.value == value)
        new BinarySearchNode[T](value, left.remove(value), right).rebalance()
      else if (right.nonEmpty && right.value == value)
        new BinarySearchNode[T](value, left, right.remove(value)).rebalance()
      else if (left.nonEmpty && ord.gt(left.value, value))
        new BinarySearchNode[T](left.value, new BinarySearchNode[T](value, left.left, left.right), right).rebalance()
      else if (right.nonEmpty && ord.lt(right.value, value))
        new BinarySearchNode[T](right.value, left, new BinarySearchNode[T](value, right.left, right.right)).rebalance()
      else
        new BinarySearchNode[T](value, left.rebalance(), right.rebalance())
    }*/

    if (left.nonEmpty && left.value == value)
      new BinarySearchNode[T](value, left.remove(value), right).rebalance()
    else if (right.nonEmpty && right.value == value)
      new BinarySearchNode[T](value, left, right.remove(value)).rebalance()
    else if (left.nonEmpty && ord.gt(left.value, value))
      new BinarySearchNode[T](left.value, new BinarySearchNode[T](value, left.left, left.right), right).rebalance()
    else if (right.nonEmpty && ord.gt(value, right.value))
      new BinarySearchNode[T](right.value, left, new BinarySearchNode[T](value, right.left, right.right)).rebalance()
    else
      new BinarySearchNode[T](value, left.rebalance(), right.rebalance())
  }
}