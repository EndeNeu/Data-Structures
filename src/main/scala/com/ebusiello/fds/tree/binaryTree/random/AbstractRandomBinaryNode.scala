package com.ebusiello.fds.tree.binaryTree.random

import com.ebusiello.fds.tree.binaryTree.BasicBinaryNode

abstract class AbstractRandomBinaryNode[T] extends BasicBinaryNode[T] {

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRandomBinaryNode[T]

  def map[V](f: (T) => V): AbstractRandomBinaryNode[V]

}
