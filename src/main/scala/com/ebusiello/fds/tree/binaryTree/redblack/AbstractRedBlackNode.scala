package com.ebusiello.fds.tree.binaryTree.redblack

import com.ebusiello.fds.tree.binaryTree.BasicBinaryNode

abstract class AbstractRedBlackNode[T] extends BasicBinaryNode[T] {

  val color: Color

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRedBlackNode[T]

  def map[V](f: (T) => V): AbstractRedBlackNode[V]

}
