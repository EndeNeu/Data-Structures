package com.ebusiello.fds.tree.binaryTree.balancedTree

import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, AbstractBinaryTree, EmptyNode}

private[binaryTree] abstract class BalancedBinaryTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, BalancedBinaryTree, BalancedBinaryTree](head) {

  def createNode(value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): AbstractBinaryNode[T]

  def createTree(head: AbstractBinaryNode[T]): BalancedBinaryTree[T]

  override def map[V](f: (T) => V): BalancedBinaryTree[V] = ???

  override def insert(mValue: T)(implicit ord: Ordering[T]): BalancedBinaryTree[T] =
    if (isEmpty) createTree(createNode(mValue, new EmptyNode[T], new EmptyNode[T]))
    else createTree(head.insert(mValue))

}

private[binaryTree] final class LeftBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends BalancedBinaryTree[T](head) {

  override def createNode(value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): AbstractBinaryNode[T] =
    new LeftBalancedBinaryNode[T](value, l, r)

  override def createTree(head: AbstractBinaryNode[T]): BalancedBinaryTree[T] =
    new LeftBalancedBinaryTree[T](head)
}

private[binaryTree] object LeftBalancedBinaryTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: LeftBalancedBinaryTree[T] = new LeftBalancedBinaryTree[T](new EmptyNode[T])
}

private[binaryTree] final class RightBalancedBinaryTree[T](head: AbstractBinaryNode[T]) extends BalancedBinaryTree[T](head) {

  override def createNode(value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): AbstractBinaryNode[T] =
    new RightBalancedBinaryNode[T](value, l, r)

  override def createTree(head: AbstractBinaryNode[T]): BalancedBinaryTree[T] =
    new RightBalancedBinaryTree[T](head)
}

private[binaryTree] object RightBalancedBinaryTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T, S] = new RightBalancedBinaryTree[T](new EmptyNode[T])
}

import scala.language.higherKinds

class D[S, T[_]]

class E[S, T[_]] extends D[S, T]

class F[S, T[_]] extends D[S, T] {

  def someM: D[S, T] = new E[S, T]

}