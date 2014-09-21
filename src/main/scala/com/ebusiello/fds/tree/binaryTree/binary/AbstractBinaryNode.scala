package com.ebusiello.fds.tree.binaryTree.binary

import com.ebusiello.fds.tree.binaryTree.BasicBinaryNode

abstract class AbstractBinaryNode[T] extends BasicBinaryNode[T] {

  def insert(mValue: T, after: T): AbstractBinaryNode[T]

  def map[V](f: T => V): AbstractBinaryNode[V]

}