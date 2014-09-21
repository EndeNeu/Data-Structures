package com.ebusiello.fds.tree.binaryTree.random

import com.ebusiello.fds.tree.{DESC, Direction, SortableTree}

/**
 * RandomBinaryTree trees have random insert and can hold duplicated values.
 * As empty node we reuse EmptyBinarySearchNode, they have the same functionality.
 */
class RandomBinaryTree[T](head: AbstractRandomBinaryNode[T]) extends AbstractRandomBinaryTree[T, RandomBinaryTree, RandomBinaryTree](head) with SortableTree[T, RandomBinaryTree] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): RandomBinaryTree[T] =
    if (isEmpty) new RandomBinaryTree[T](new RandomBinaryNode[T](mValue, new EmptyRandomBinaryNode, new EmptyRandomBinaryNode))
    else new RandomBinaryTree[T](head.insert(mValue))

  override def map[V](f: (T) => V): RandomBinaryTree[V] =
    if (isEmpty) new RandomBinaryTree[V](new EmptyRandomBinaryNode[V])
    else head match {
      case searchNode: RandomBinaryNode[T] => new RandomBinaryTree[V](searchNode.map(f))
    }

  /**
   * Sorting unordered tree is implemented via shuffling (the order is random and direction doesn't matter)
   */
  override def sort(direction: Direction = DESC)(implicit ord: Ordering[T]): RandomBinaryTree[T] = shuffle

  /**
   * Shuffle a tree.
   */
  def shuffle(implicit ord: Ordering[T]): RandomBinaryTree[T] = {
    val randomizer = new scala.util.Random()
    val shuffled: List[T] = randomizer.shuffle(foldTree(List.empty[T])(_ :+ _)(_ ++ _))
    RandomBinaryTree.fromColl(shuffled)
  }
}

object RandomBinaryTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): RandomBinaryTree[T] =
    coll.foldLeft(RandomBinaryTree.emptyTree[T])(_.insert(_))

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: RandomBinaryTree[T] = new RandomBinaryTree[T](new EmptyRandomBinaryNode[T])
}