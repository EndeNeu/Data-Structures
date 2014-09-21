package com.ebusiello.fds.tree.binaryTree.binary

import com.ebusiello.fds.tree.binaryTree.random.EmptyRandomBinaryNode
import com.ebusiello.fds.tree.{Direction, SortableTree}
import com.ebusiello.fds.tree.binaryTree.BasicBinaryNode

class BinaryTree[T](val head: BasicBinaryNode[T]) extends AbstractBinaryTree(head) with SortableTree[T, BinaryTree] {

  override def sort(direction: Direction)(implicit ord: Ordering[T]): BinaryTree[T] = ???

  override def insert(mValue: T, after: T): BinaryTree[T] =
    if (isEmpty) new BinaryTree[T](new BinaryNode[T](mValue, new EmptyBinaryNode[T], new EmptyBinaryNode[T]))
    else head match {
      case bn: BinaryNode[T] => new BinaryTree[T](bn.insert(mValue, after))
    }

  override def map[V](f: (T) => V): BinaryTree[V] =
    if (isEmpty) new BinaryTree[V](new EmptyBinaryNode[V])
    else head match {
      case binaryNode: BinaryNode[T] => new BinaryTree[V](binaryNode.map(f))
    }

}

object BinaryTree {

  def fromColl[T, S <: TraversableOnce[(T, T)]](coll: S)(implicit ord: Ordering[T]): BinaryTree[T] =
    coll.foldLeft(BinaryTree.emptyTree[T])((tree, curr) => tree.insert(curr._1, curr._2))

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: BinaryTree[T] = new BinaryTree[T](new EmptyRandomBinaryNode[T])
}