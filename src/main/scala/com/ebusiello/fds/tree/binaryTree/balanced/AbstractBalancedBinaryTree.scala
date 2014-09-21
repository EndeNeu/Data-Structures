package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, AbstractBinaryTree}
import scala.language.higherKinds

/**
 * Abstract balanced tree
 */
private[binaryTree] abstract class AbstractBalancedBinaryTree[T, S[_], A[_]](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, S, A](head) {

  def toRight: RightBalancedBinaryTree[T]

  def toLeft: LeftBalancedBinaryTree[T]

}