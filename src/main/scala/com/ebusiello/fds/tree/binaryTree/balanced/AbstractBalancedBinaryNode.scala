package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

/**
 * Balanced node abstraction.
 */
private[binaryTree] abstract class AbstractBalancedBinaryNode[T] extends AbstractBinaryNode[T] {

  def toRight: AbstractBalancedBinaryNode[T]

  def toLeft: AbstractBalancedBinaryNode[T]

  def map[V, VLR <: AbstractBalancedBinaryNode[V]](f: T => V): AbstractBinaryNode[V]

  def leftRelativeDepth: Int

  def rightRelativeDepth: Int

}