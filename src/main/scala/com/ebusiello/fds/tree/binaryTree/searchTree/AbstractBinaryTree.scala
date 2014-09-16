package com.ebusiello.fds.tree.binaryTree.searchTree

import com.ebusiello.fds.tree.Tree
import com.ebusiello.fds.tree.binaryTree.EmptyNode
import scala.language.higherKinds

/**
 * Holds common operations for trees and nodes
 */
trait AbstractBinaryCommon[T] {

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


/**
 *
 */
private[binaryTree] abstract class AbstractBinaryTree[T, S[_]] extends AbstractBinaryCommon[T] with Tree {

  def insert(mValue: T)(implicit ord: Ordering[T]): S[T]

  // TODO map should return a normal binary tree because the ordering clause could not be respected
  def map[V](f: T => V): BinarySearchTree[V]

}


private[binaryTree] abstract class AbstractBinaryNode[T] extends AbstractBinaryCommon[T] {

  def toTree: BinarySearchTree[T] = this.asInstanceOf[BinarySearchTree[T]]

  def toBinaryNode: BinaryNode[T] = this.asInstanceOf[BinaryNode[T]]

  def toEmptyNode: EmptyNode[T] = this.asInstanceOf[EmptyNode[T]]

  def insert(mValue: T)(implicit ord: Ordering[T]): BinaryNode[T]

  def map[V](f: T => V): AbstractBinaryNode[V]

}



