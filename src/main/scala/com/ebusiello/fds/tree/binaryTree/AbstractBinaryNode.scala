package com.ebusiello.fds.tree.binaryTree

private[binaryTree] abstract class AbstractBinaryNode[T] extends BinaryCommon[T] {

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T]

  def map[V](f: T => V): AbstractBinaryNode[V]

  def leftRelativeDepth: Int

  def rightRelativeDepth: Int

}