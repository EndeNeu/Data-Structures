package com.ebusiello.data.structure.immutable.trees.binaries.avl

import com.ebusiello.data.structure.immutable.trees.binaries.{BinaryTreeException, GenericBinaryNode, GenericBinaryTree}
import com.ebusiello.data.structure.immutable.trees.generic.node.{BalanceableNode, RetractableNode, RotableNode, OrderableNode}
import com.ebusiello.data.structure.immutable.trees.generic.tree.BalanceableTree

/**
 * In an AVL tree, the heights of the two child subtrees of any node differ by at most one;
 * if at any time they differ by more than one, rebalancing is done to restore this property.
 */
final class AVLTree[T] private (val head: AVLNode[T]) extends GenericBinaryTree[T, AVLTree, AVLNode] with BalanceableTree[T, AVLTree] {

  def this() = this(new EmptyAVLNode[T])

  override def insert(value: T)(implicit ord: Ordering[T]): AVLTree[T] =
    if (isEmpty) new AVLTree[T](new AVLNode[T](value, new EmptyAVLNode[T], new EmptyAVLNode[T]))
    else new AVLTree[T](head.insert(value).rebalance())

  override def remove(v: T)(implicit ord: Ordering[T]): AVLTree[T] =
    if (isEmpty) throw new BinaryTreeException("Remove on empty tree.")
    else new AVLTree[T](head.remove(v).rebalance())

  /**
   * Map a tree to another tree, no need to balance the tree, depth is balanced on insert.
   */
  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): AVLTree[V] =
    if (isEmpty) new AVLTree[V](new EmptyAVLNode[V])
    else new AVLTree[V](head.map(f)).resort()

  override def resort()(implicit ord: Ordering[T]): AVLTree[T] = {
    def loop(previousTree: AVLTree[T]): AVLTree[T] = {
      // first rebalance of the tree
      val rebalanced = new AVLTree[T](previousTree.head.resort())
      // folding keeps the elemnt order, if the tree hasn't rotated the order will be the same
      // and we need to break the loop
      if (rebalanced.foldTree(List.empty[T])((acc, curr) => acc :+ curr)((a1, a2) => a1 ++ a2) == previousTree.foldTree(List.empty[T])((acc, curr) => acc :+ curr)((a1, a2) => a1 ++ a2))
        rebalanced
      // otherwise the tree still needs rebalancing.
      else
        loop(rebalanced)
    }
    loop(this)
  }
}

object AVLTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): AVLTree[T] =
    coll.foldLeft(AVLTree.emptyTree[T])((tree, curr) => tree.insert(curr))

  def emptyTree[T]: AVLTree[T] =
    new AVLTree[T](new EmptyAVLNode[T])
}

private[avl] class AVLNode[T](val value: T, val left: AVLNode[T], val right: AVLNode[T]) extends GenericBinaryNode[T, AVLNode] with OrderableNode[T, AVLNode] with RotableNode[T, AVLNode] with RetractableNode[T, AVLNode] with BalanceableNode[T, AVLNode] {

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

private[avl] final class EmptyAVLNode[T] extends AVLNode[T](null.asInstanceOf[T], null, null) {

  override def map[V](f: (T) => V): EmptyAVLNode[V] =
    new EmptyAVLNode[V]

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def insert(newValue: T)(implicit ord: Ordering[T]): AVLNode[T] =
    new AVLNode[T](newValue, new EmptyAVLNode[T], new EmptyAVLNode[T])

  override def remove(v: T): AVLNode[T] =
    this

  override def nonEmpty: Boolean =
    !isEmpty

  override def stringify: String = "E"

  override def length: Int = 0

  override def depth: Int = 0

  override protected def rotateNode(): AVLNode[T] = this

  override def relativeDepth: Int = 0

  override def rebalance()(implicit ord: Ordering[T]): AVLNode[T] = this

  override def resort()(implicit ord: Ordering[T]): AVLNode[T] = this
}
