package com.ebusiello.fds.tree.binaryTree


/**
 * Holds common operations for trees and nodes
 */
trait BinaryCommon[T] {

  /**
   * Find a value in a tree.
   */
  def find(mValue: T)(implicit ord: Ordering[T]): Boolean

  /**
   * Fold a tree.
   */
  def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S

  /**
   * Reduce a tree
   */
  def reduceBinaryTree[S](f: (T, T) => T): T

  /**
   * if a tree/node is empty or not.
   */
  def isEmpty: Boolean


}
