package com.ebusiello.fds.tree.binaryTree.redblack

final class RedBlackEmptyNode[T](mColor: Color) extends AbstractRedBlackNode[T] {
  override def leftRelativeDepth: Int =
    0

  override def rightRelativeDepth: Int =
    0

  /**
   * if a tree/node is empty or not.
   */
  override def isEmpty: Boolean =
    true

  /**
   * Find a value in a tree.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  /**
   * Fold a tree.
   */
  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override val color: Color = mColor

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRedBlackNode[T] = color match {
    case BLACK => new BlackNode[T](mValue, new RedBlackEmptyNode[T](RED), new RedBlackEmptyNode[T](RED))
    case RED => new RedNode[T](mValue, new RedBlackEmptyNode[T](BLACK), new RedBlackEmptyNode[T](BLACK))
  }

  override def map[V](f: (T) => V): AbstractRedBlackNode[V] =
    new RedBlackEmptyNode[V](color)
}