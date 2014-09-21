package com.ebusiello.fds.tree.binaryTree.unordered

import com.ebusiello.fds.tree.{DESC, Direction, SortableTree}
import com.ebusiello.fds.tree.binaryTree.search.{BinarySearchNode, EmptyBinarySearchNode}
import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, AbstractBinaryTree}

/**
 * Unordered trees have random insert and can hold duplicated values.
 * As empty node we reuse EmptyBinarySearchNode, they have the same functionality.
 */
class UnorderedBinaryTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, UnorderedBinaryTree, UnorderedBinaryTree](head) with SortableTree[T, UnorderedBinaryTree] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): UnorderedBinaryTree[T] =
    if (isEmpty) new UnorderedBinaryTree[T](new BinarySearchNode[T](mValue, new EmptyBinarySearchNode, new EmptyBinarySearchNode))
    else new UnorderedBinaryTree[T](head.insert(mValue))

  override def map[V](f: (T) => V): UnorderedBinaryTree[V] =
    if (isEmpty) new UnorderedBinaryTree[V](new EmptyBinarySearchNode[V])
    else head match {
      case searchNode: UnorderedBinaryNode[T] => new UnorderedBinaryTree[V](searchNode.map(f))
    }

  /**
   * Sorting unordered tree is implemented via shuffling (the order is random and direction doesn't matter)
   */
  override def sort(direction: Direction = DESC)(implicit ord: Ordering[T]): UnorderedBinaryTree[T] = shuffle

  /**
   * Shuffle a tree.
   */
  def shuffle(implicit ord: Ordering[T]): UnorderedBinaryTree[T] = {
    val randomizer = new scala.util.Random()
    val shuffled: List[T] = randomizer.shuffle(foldTree(List.empty[T])(_ :+ _)(_ ++ _))
    UnorderedBinaryTree.fromColl(shuffled)
  }
}

object UnorderedBinaryTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): UnorderedBinaryTree[T] =
    coll.foldLeft(UnorderedBinaryTree.emptyTree[T])(_.insert(_))

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: UnorderedBinaryTree[T] = new UnorderedBinaryTree[T](new EmptyBinarySearchNode[T])
}