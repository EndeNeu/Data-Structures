package com.ebusiello.fds.tree.binaryTree

private[binaryTree] abstract class AbstractBinaryNode[T] extends BinaryCommon[T] {

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T]

  def leftRelativeDepth: Int

  def rightRelativeDepth: Int

}