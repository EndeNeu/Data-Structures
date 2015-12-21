package com.ebusiello.fds.tree.binaries.binary

import com.ebusiello.fds.tree.binaries.GenericBinaryTree

class BinaryTree[T](val head: BinaryNode[T]) extends GenericBinaryTree[T, BinaryTree, BinaryNode] {

  override def insert(value: T)(implicit ord: Ordering[T]): BinaryTree[T] =
    if (isEmpty) new BinaryTree[T](new BinaryNode[T](value, new EmptyBinaryNode[T], new EmptyBinaryNode[T]))
    else new BinaryTree[T](head.insert(value))

  override def remove(v: T): BinaryTree[T] =
    if (isEmpty) this
    else new BinaryTree[T](head.remove(v))

  /**
   * Map a com.ebusiello.fds.tree to another com.ebusiello.fds.tree, two orderings are needed to allow the com.ebusiello.fds.tree to change its type parameter,
   * for example a com.ebusiello.fds.tree of ints could be mapped to a com.ebusiello.fds.tree of chars and we need an orderable for that too.
   *
   * After having mapped, we need to resort the com.ebusiello.fds.tree to respect the order.
   */
  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): BinaryTree[V] =
    if (isEmpty) new BinaryTree[V](new EmptyBinaryNode[V])
    else new BinaryTree[V](head.map(f)).resort()(ord2)

  /**
   * Resort the com.ebusiello.fds.tree, used after mapping and in general trying to mutate the com.ebusiello.fds.tree.
   */
  private def resort()(implicit ord: Ordering[T]): BinaryTree[T] = {
    val folded: List[T] = head.foldTree(List.empty[T])((acc, curr) => acc :+ curr)((a1, a2) => a1 ++ a2)
    println(folded)
    BinaryTree.fromColl[T, List[T]](folded)
  }

  override def toString: String =
    stringify

}

object BinaryTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): BinaryTree[T] =
    coll.foldLeft(BinaryTree.emptyTree[T])((tree, curr) => tree.insert(curr))

  def emptyTree[T]: BinaryTree[T] =
    new BinaryTree[T](new EmptyBinaryNode[T])
}