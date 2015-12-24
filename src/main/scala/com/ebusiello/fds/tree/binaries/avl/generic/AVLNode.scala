package com.ebusiello.fds.tree.binaries.avl.generic

import com.ebusiello.fds.tree.binaries.GenericBinaryNode
import com.ebusiello.fds.tree.generic.node.{ RetractableNode, OrderableNode, RotableNode, BalanceableNode }

class AVLNode[T](val value: T, val left: AVLNode[T], val right: AVLNode[T]) extends GenericBinaryNode[T, AVLNode] with OrderableNode[T, AVLNode] with RotableNode[T, AVLNode] with RetractableNode[T, AVLNode] with BalanceableNode[T, AVLNode] {

  override def insert(newValue: T)(implicit ord: Ordering[T]): AVLNode[T] = {
    if (newValue == value) this // guard against duplicates.
    else if (ord.lt(newValue, value)) new AVLNode(value, left.insert(newValue), right)
    else new AVLNode(value, left, right.insert(newValue))
  }

  override def remove(v: T): AVLNode[T] = {
    if (v == value) {
      // if this node is to be removed and the left leaf is non empty pick that to replace this node
      if (left.nonEmpty && right.isEmpty) new AVLNode[T](left.value, new EmptyAVLNode[T], right)
      // else do the same with the right
      else if (right.nonEmpty && left.isEmpty) new AVLNode[T](right.value, left, new EmptyAVLNode[T])
      // else if both are empty just return an empty node.
      else if (right.isEmpty && left.isEmpty) new EmptyAVLNode[T]
      // if both are not empty, look for the first right node with a left empty node
      // and rotate the tree, after that delete the value who has rotated.
      else rotateNode()
    } else new AVLNode[T](value, left.remove(v), right.remove(v))
  }

  /**
   * Find the leftmost node with an empty left leaf.
   */
  private def findNoLeftNode: AVLNode[T] = {
    if (left.isEmpty) this
    else left.findNoLeftNode
  }

  /**
   * Rotate a tree starting from the right node.
   *
   * @see https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeletenotrightchildexample.jpg
   */
  override protected def rotateNode(): AVLNode[T] = {
    val newVal = right.findNoLeftNode.value
    new AVLNode[T](newVal, left, right.remove(newVal))
  }

  override def map[V](f: (T) => V): AVLNode[V] =
    new AVLNode[V](f(value), left.map(f), right.map(f))

  /**
   * This particular tree needs rebalancing when inserting/deleting since the relative depth of each subtree of each
   * node must be in the range [-1, 1], if the range is over this, the tree needs rebalancing
   *
   * @see https://en.wikipedia.org/wiki/AVL_tree#/media/File:AVL_Tree_Rebalancing.svg
   *
   * @param ord
   * @return
   */
  override def rebalance()(implicit ord: Ordering[T]): AVLNode[T] = {
    val range = left.relativeDepth - right.relativeDepth
    if (range <= 1 && range >= -1) this
    else if (range > 1) {
      // left-left case
      if (left.left.nonEmpty && left.right.isEmpty)
        new AVLNode[T](left.value, left.left, new AVLNode[T](value, left.right, right)).rebalance()
      // left-right case
      else // if(left.left.isEmpty && left.right.nonEmpty)
        new AVLNode[T](value, new AVLNode[T](left.right.value, new AVLNode[T](left.value, left.left, left.right.left), left.right.right), right).rebalance()
    } else {
      // right-right case
      if (right.left.isEmpty && right.right.nonEmpty)
        new AVLNode[T](right.value, new AVLNode[T](value, left, right.left), right.right).rebalance()
      // right-left case
      else // if(right.left.nonEmpty && right.right.isEmpty)
        new AVLNode[T](value, left, new AVLNode[T](right.left.value, right.left.left, new AVLNode[T](right.value, right.left.right, right.right.right))).rebalance()
    }
  }

  override def resort()(implicit ord: Ordering[T]): AVLNode[T] = {
    // avoid duplication on both left and right.
    if (left.nonEmpty && left.value == value)
      new AVLNode[T](value, left.remove(value), right).resort()
    else if (right.nonEmpty && right.value == value)
      new AVLNode[T](value, left, right.remove(value)).resort()
    // balance the left part
    else if (left.nonEmpty && ord.gt(left.value, value))
      new AVLNode[T](left.value, new AVLNode[T](value, left.left, left.right), right).resort()
    // balance the right part
    else if (right.nonEmpty && ord.gt(value, right.value))
      new AVLNode[T](right.value, left, new AVLNode[T](value, right.left, right.right)).resort()
    // node already balanced
    else
      new AVLNode[T](value, left.resort(), right.resort())
  }
}

object AVLNode {
  def unapply[T](t: AVLNode[T]): Option[(T, AVLNode[T], AVLNode[T])] =
    Option((t.value, t.left, t.right))
}