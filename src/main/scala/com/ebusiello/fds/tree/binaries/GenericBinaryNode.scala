package com.ebusiello.fds.tree.binaries

import com.ebusiello.fds.tree.generic.node.{ RemoveableNode, FindableNode, Node, StringifiableNode }

import scala.language.higherKinds

/**
 * Generalization of a Binary Node.
 *
 * @tparam T the type of the value held by this node.
 * @tparam S the type of node.
 */
private[tree] trait GenericBinaryNode[T, S[_]] extends Node[T] with FindableNode[T] with StringifiableNode with RemoveableNode[T, S] {

  val left: S[T]

  val right: S[T]

  def map[V](f: T => V): S[V]

  def depth: Int

  def length: Int
}