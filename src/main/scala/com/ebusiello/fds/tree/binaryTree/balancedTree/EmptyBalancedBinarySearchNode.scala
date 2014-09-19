package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.searchTree.BinarySearchNode
import com.ebusiello.fds.tree.binaryTree.AbstractBinaryNode

private[binaryTree] final class EmptyBalancedBinarySearchNode[T, LR <: BalancedBinaryNode[_]] extends AbstractBalancedBinaryNode[T] {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    new BinarySearchNode[T](mValue, new EmptyBalancedBinarySearchNode[T, LR], new EmptyBalancedBinarySearchNode[T, LR])

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new EmptyBalancedBinarySearchNode[V, LR]

  override def toRight: AbstractBalancedBinaryNode[T] =
    this

  override def toLeft: AbstractBalancedBinaryNode[T] =
    this

  override def leftRelativeDepth: Int =
    0

  override def rightRelativeDepth: Int =
    0

  override def toString =
    "E"

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

}