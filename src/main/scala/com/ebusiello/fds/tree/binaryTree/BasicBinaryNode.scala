package com.ebusiello.fds.tree.binaryTree

private[binaryTree] abstract class BasicBinaryNode[T] extends BinaryCommon[T] {

  def leftRelativeDepth: Int

  def rightRelativeDepth: Int

}