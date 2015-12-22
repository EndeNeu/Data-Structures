package com.ebusiello.fds.tree.binaries.binary

import com.ebusiello.fds.tree.binaries.GenericBinaryTree

class BinarySearchTree[T](val head: BinarySearchNode[T]) extends GenericBinaryTree[T, BinarySearchTree, BinarySearchNode] {

  override def insert(value: T)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    if (isEmpty) new BinarySearchTree[T](new BinarySearchNode[T](value, new EmptyBinarySearchNode[T], new EmptyBinarySearchNode[T]))
    else new BinarySearchTree[T](head.insert(value))

  override def remove(v: T): BinarySearchTree[T] =
    if (isEmpty) this
    else new BinarySearchTree[T](head.remove(v))

  /**
   * Map a com.ebusiello.fds.tree to another com.ebusiello.fds.tree, two orderings are needed to allow the com.ebusiello.fds.tree to change its type parameter,
   * for example a com.ebusiello.fds.tree of ints could be mapped to a com.ebusiello.fds.tree of chars and we need an orderable for that too.
   *
   * After having mapped, we need to resort the com.ebusiello.fds.tree to respect the order.
   */
  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): BinarySearchTree[V] =
    if (isEmpty) new BinarySearchTree[V](new EmptyBinarySearchNode[V])
    else new BinarySearchTree[V](head.map(f)).resort()(ord2)

  /**
   * Resort the com.ebusiello.fds.tree, used after mapping and in general trying to mutate the com.ebusiello.fds.tree.
   */
  private def resort()(implicit ord: Ordering[T]): BinarySearchTree[T] = {
    val folded: List[T] = head.foldTree(List.empty[T])((acc, curr) => acc :+ curr)((a1, a2) => a1 ++ a2)
    println(folded)
    BinarySearchTree.fromColl[T, List[T]](folded)
  }

}

object BinarySearchTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    coll.foldLeft(BinarySearchTree.emptyTree[T])((tree, curr) => tree.insert(curr))

  def emptyTree[T]: BinarySearchTree[T] =
    new BinarySearchTree[T](new EmptyBinarySearchNode[T])
}