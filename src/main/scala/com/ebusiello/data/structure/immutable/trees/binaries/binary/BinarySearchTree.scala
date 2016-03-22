package com.ebusiello.data.structure.immutable.trees.binaries.binary

import com.ebusiello.data.structure.immutable.trees.binaries.{GenericBinaryNode, GenericBinaryTree}
import com.ebusiello.data.structure.immutable.trees.generic.node.{RotableNode, OrderableNode, BalanceableNode}

/**
 * https://en.wikipedia.org/wiki/Binary_search_tree
 */
final class BinarySearchTree[T] private (val head: BinarySearchNode[T]) extends GenericBinaryTree[T, BinarySearchTree, BinarySearchNode] with BalanceableNode[T, BinarySearchTree] {

  def this() = this(new EmptyBinarySearchNode[T])

  override def insert(value: T)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    if (isEmpty) new BinarySearchTree[T](new BinarySearchNode[T](value, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T]))
    else new BinarySearchTree[T](head.insert(value))

  override def remove(v: T)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    if (isEmpty) this
    else new BinarySearchTree[T](head.remove(v))

  /**
   * Map a tree to another tree, two orderings are needed to allow the tree to change its type parameter,
   * for example a tree of ints could be mapped to a tree of chars and we need an orderable for that too.
   *
   * After having mapped, we need to "rotate the tree around the root" to respect the order.
   *
   *
   *     5  -> map _ * -1   -5   -> rebalance ->    -5
   *    / \                / \                      / \
   *   4   6             -4 -6                    -6  -4
   *  /\  /\             /\ /\                    /\  /\
   * 2 E E 7           -2 EE -7                 -7 E E -2
   *
   */
  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): BinarySearchTree[V] =
    if (isEmpty) new BinarySearchTree[V](new EmptyBinarySearchNode[V])
    else new BinarySearchTree[V](head.map(f)).resort()(ord2)

  /**
   * Rebalance a tree, usually after a map, would be nice to handle at node level but currently
   * the problem is that the tree needs to be rebalanced at each rotation because
   * it may have changed the relation between the previous node and the one currently rotated
   * which may also leed to another rotation, for now the only way is to start a rebalance from
   * the head every time the rotation reach the end of the tree, if the tree isn't changing we
   * are done with the rebalancing.
   */
  override def resort()(implicit ord: Ordering[T]): BinarySearchTree[T] = {
    def loop(previousTree: BinarySearchTree[T]): BinarySearchTree[T] = {
      // first rebalance of the tree
      val rebalanced = new BinarySearchTree[T](previousTree.head.resort())
      // folding keeps the element order, if the tree hasn't rotated the order will be the same
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

object BinarySearchTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    coll.foldLeft(BinarySearchTree.emptyTree[T])((tree, curr) => tree.insert(curr))

  def emptyTree[T]: BinarySearchTree[T] =
    new BinarySearchTree[T](new EmptyBinarySearchNode[T])
}

private[binary] class BinarySearchNode[T](val value: T, val left: BinarySearchNode[T], val right: BinarySearchNode[T]) extends GenericBinaryNode[T, BinarySearchNode] with OrderableNode[T, BinarySearchNode] with RotableNode[T, BinarySearchNode] with BalanceableNode[T, BinarySearchNode] {

  /**
    * Insert can happen between leafs, ordering is preserved
    *
    *
    *   1  insert(4)    1
    *  / \             / \
    * 2  5            2  5
    *   /            /\  /\
    *  3            E E 3 4
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

  /**
    * Balance the node in its context, the node is rebalanced recursively
    * until it's balanced with its left and right leaves.
    */
  override def resort()(implicit ord: Ordering[T]): BinarySearchNode[T] = {
    // avoid duplication on both left and right.
    if (left.nonEmpty && left.value == value)
      new BinarySearchNode[T](value, left.remove(value), right).resort()
    else if (right.nonEmpty && right.value == value)
      new BinarySearchNode[T](value, left, right.remove(value)).resort()
    // balance the left part
    else if (left.nonEmpty && ord.gt(left.value, value))
      new BinarySearchNode[T](left.value, new BinarySearchNode[T](value, left.left, left.right), right).resort()
    // balance the right part
    else if (right.nonEmpty && ord.gt(value, right.value))
      new BinarySearchNode[T](right.value, left, new BinarySearchNode[T](value, right.left, right.right)).resort()
    // node already balanced
    else
      new BinarySearchNode[T](value, left.resort(), right.resort())
  }
}

private[binary] final class EmptyBinarySearchNode[T] extends BinarySearchNode[T](null.asInstanceOf[T], null, null) {

  override def map[V](f: (T) => V): EmptyBinarySearchNode[V] = new EmptyBinarySearchNode[V]

  override def isEmpty: Boolean = true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = z

  override def insert(newValue: T)(implicit ord: Ordering[T]): BinarySearchNode[T] =
    new BinarySearchNode[T](newValue, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T])

  override def remove(v: T): BinarySearchNode[T] = this

  override def nonEmpty: Boolean = !isEmpty

  override def stringify: String = "E"

  override def length: Int = 0

  override def depth: Int = 0

  override def relativeDepth: Int = 0

  override protected def rotateNode(): BinarySearchNode[T] = this

  override def resort()(implicit ord: Ordering[T]): BinarySearchNode[T] =
    this

}