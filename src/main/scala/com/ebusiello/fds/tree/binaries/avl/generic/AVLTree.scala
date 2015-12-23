package com.ebusiello.fds.tree.binaries.avl.generic

import com.ebusiello.fds.tree.binaries.GenericBinaryTree
import com.ebusiello.fds.tree.generic.tree.BalanceableTree

class AVLTree[T](val head: AVLNode[T]) extends GenericBinaryTree[T, AVLTree, AVLNode] with BalanceableTree[T, AVLTree] {

  override def insert(value: T)(implicit ord: Ordering[T]): AVLTree[T] =
    if (isEmpty) new AVLTree[T](new AVLNode[T](value, new EmptyAVLNode[T], new EmptyAVLNode[T]))
    else new AVLTree[T](head.insert(value)).rebalance()

  override def remove(v: T)(implicit ord: Ordering[T]): AVLTree[T] =
    if (isEmpty) this
    else new AVLTree[T](head.remove(v)).rebalance()

  /**
   * Map a com.ebusiello.fds.tree to another com.ebusiello.fds.tree, two orderings are needed to allow the com.ebusiello.fds.tree to change its type parameter,
   * for example a com.ebusiello.fds.tree of ints could be mapped to a com.ebusiello.fds.tree of chars and we need an orderable for that too.
   *
   * After having mapped, we need to resort the com.ebusiello.fds.tree to respect the order.
   */
  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): AVLTree[V] =
    if (isEmpty) new AVLTree[V](new EmptyAVLNode[V])
    else new AVLTree[V](head.map(f)).rebalance()

  /**
   * Rebalance a tree, usually after a map, would be nice to handle at node level but currently
   * the problem is that the tree needs to be rebalanced at each rotation because
   * it may have changed the relation between the previous node and the one currently rotated
   * which may also leed to another rotation, for now the only way is to start a rebalance from
   * the head every time the rotation reach the end of the tree, if the tree isn't changing we
   * are done with the rebalancing.
   */
  override def rebalance()(implicit ord: Ordering[T]): AVLTree[T] = {
    def loop(previousTree: AVLTree[T]): AVLTree[T] = {
      // first rebalance of the tree
      val rebalanced = new AVLTree[T](previousTree.head.rebalance())
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