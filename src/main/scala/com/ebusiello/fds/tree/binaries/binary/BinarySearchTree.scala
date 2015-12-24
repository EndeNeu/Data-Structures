package com.ebusiello.fds.tree.binaries.binary

import com.ebusiello.fds.tree.binaries.GenericBinaryTree
import com.ebusiello.fds.tree.generic.node.BalanceableNode

final class BinarySearchTree[T](val head: BinarySearchNode[T]) extends GenericBinaryTree[T, BinarySearchTree, BinarySearchNode] with BalanceableNode[T, BinarySearchTree] {

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

object BinarySearchTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    coll.foldLeft(BinarySearchTree.emptyTree[T])((tree, curr) => tree.insert(curr))

  def emptyTree[T]: BinarySearchTree[T] =
    new BinarySearchTree[T](new EmptyBinarySearchNode[T])
}