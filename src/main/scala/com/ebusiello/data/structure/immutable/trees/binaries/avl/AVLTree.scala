package com.ebusiello.data.structure.immutable.trees.binaries.avl

import com.ebusiello.data.structure.immutable.trees.binaries.GenericBinaryTree
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
    if (isEmpty) this
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