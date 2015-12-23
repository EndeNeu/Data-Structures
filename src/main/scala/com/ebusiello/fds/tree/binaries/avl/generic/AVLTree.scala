package com.ebusiello.fds.tree.binaries.avl.generic

import com.ebusiello.fds.tree.binaries.GenericBinaryTree

class AVLTree[T](val head: AVLNode[T]) extends GenericBinaryTree[T, AVLTree, AVLNode] {

  override def insert(value: T)(implicit ord: Ordering[T]): AVLTree[T] =
    if (isEmpty) new AVLTree[T](new AVLNode[T](value, new EmptyAVLNode[T], new EmptyAVLNode[T]))
    else new AVLTree[T](head.insert(value))

  override def remove(v: T)(implicit ord: Ordering[T]): AVLTree[T] =
    if (isEmpty) this
    else new AVLTree[T](head.remove(v))

  /**
   * Map a tree to another tree, no need to balance the tree, depth is balanced on insert.
   */
  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): AVLTree[V] =
    if (isEmpty) new AVLTree[V](new EmptyAVLNode[V])
    else new AVLTree[V](head.map(f))

}

object AVLTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): AVLTree[T] =
    coll.foldLeft(AVLTree.emptyTree[T])((tree, curr) => tree.insert(curr))

  def emptyTree[T]: AVLTree[T] =
    new AVLTree[T](new EmptyAVLNode[T])
}