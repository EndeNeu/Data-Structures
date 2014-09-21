package com.ebusiello.fds.tree.binaryTree.balanced

import com.ebusiello.fds.tree.binaryTree.BasicBinaryNode
import scala.language.higherKinds

/**
 * Balanced node abstraction.
 */
private[binaryTree] abstract class AbstractBalancedBinaryNode[T] extends BasicBinaryNode[T] {

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBalancedBinaryNode[T]

  def toRight: AbstractBalancedBinaryNode[T]

  def toLeft: AbstractBalancedBinaryNode[T]

  def map[V, VLR <: AbstractBalancedBinaryNode[V]](f: T => V): AbstractBalancedBinaryNode[V]

}