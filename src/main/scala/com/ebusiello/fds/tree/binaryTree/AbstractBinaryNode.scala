package com.ebusiello.fds.tree.binaryTree

import com.ebusiello.fds.tree.binaryTree.searchTree.{BinarySearchNode, BinarySearchTree}

private[binaryTree] abstract class AbstractBinaryNode[T] extends BinaryCommon[T] {

  def toTree: BinarySearchTree[T] = this.asInstanceOf[BinarySearchTree[T]]

  def toBinaryNode: BinarySearchNode[T] = this.asInstanceOf[BinarySearchNode[T]]

  def toEmptyNode: EmptyNode[T] = this.asInstanceOf[EmptyNode[T]]

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T]

  def map[V](f: T => V): AbstractBinaryNode[V]

  def leftRelativeDepth: Int

  def rightRelativeDepth: Int

}