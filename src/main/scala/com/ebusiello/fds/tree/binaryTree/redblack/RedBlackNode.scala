//package com.ebusiello.fds.tree.binaryTree.redblack
//
//abstract class RedBlackNode[T](val value: T, mColor: Color, val left: AbstractRedBlackNode[T], val right: AbstractRedBlackNode[T]) extends AbstractRedBlackNode[T] {
//
//  override def leftRelativeDepth: Int =
//    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + left.leftRelativeDepth
//
//  override def rightRelativeDepth: Int =
//    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + right.rightRelativeDepth
//
//  /**
//   * if a tree/node is empty or not.
//   */
//  override def isEmpty: Boolean =
//    false
//
//  /**
//   * Find a value in a tree.
//   */
//  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = ???
//
//  /**
//   * Fold a tree.
//   */
//  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = ???
//
//  override val color: Color = mColor
//
//}
