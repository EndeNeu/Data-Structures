package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, AbstractBinaryTree}

/**
 * Abstract balanced tree
 */
private[binaryTree] abstract class AbstractBalancedBinaryTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, AbstractBalancedBinaryTree, AbstractBalancedBinaryTree](head) {

  def toRight: RightBalancedBinaryTree[T]

  def toLeft: LeftBalancedBinaryTree[T]

}