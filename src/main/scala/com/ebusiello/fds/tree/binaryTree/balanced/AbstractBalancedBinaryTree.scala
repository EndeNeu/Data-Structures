package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.binaryTree.BasicBinaryTreeWithInsert

import scala.language.higherKinds

/**
 * Abstract balanced tree (AKA perfect tree).
 */
private[binaryTree] abstract class AbstractBalancedBinaryTree[T, S[_], A[_]](val head: AbstractBalancedBinaryNode[T]) extends BasicBinaryTreeWithInsert[T, S, A](head) {

  def toRight: RightBalancedBinaryTree[T]

  def toLeft: LeftBalancedBinaryTree[T]

}