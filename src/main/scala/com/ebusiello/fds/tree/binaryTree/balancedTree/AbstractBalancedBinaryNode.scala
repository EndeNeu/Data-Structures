package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

/**
 * Balanced node abstraction.
 */
private[binaryTree] abstract class AbstractBalancedBinaryNode[T] extends AbstractBinaryNode[T] {

  def toRight: AbstractBalancedBinaryNode[T]

  def toLeft: AbstractBalancedBinaryNode[T]
}