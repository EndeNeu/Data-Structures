package com.ebusiello.fds.tree.binaryTree.binary

import com.ebusiello.fds.tree.binaryTree.{BasicBinaryNode, BasicBinaryTree}

abstract class AbstractBinaryTree[T](head: BasicBinaryNode[T]) extends BasicBinaryTree[T, BinaryTree, BinaryTree](head) {

  def insert(mValue: T, after: T): BinaryTree[T]

}