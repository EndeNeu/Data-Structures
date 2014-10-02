package com.ebusiello.fds.tree.binaryTree.search

import com.ebusiello.fds.tree.binaryTree.BasicBinaryNode
import com.ebusiello.fds.tree.binaryTree.balanced.AbstractBalancedBinaryNode

/**
 * This class is needed to have a common super type with a map method inside for both binary search nodes
 * and binary empty search nodes.
 */
private[binaryTree] abstract class AbstractBinarySearchNode[T] extends BasicBinaryNode[T] {
  def map[V](f: T => V): AbstractBalancedBinaryNode[V]

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T]

  def delete(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T]

}
