package com.ebusiello.fds.tree.generic.node

trait Node[T] {

  val value: T

  def isEmpty: Boolean

  def nonEmpty = !isEmpty

  def foldTree[P](z: P)(f: (P, T) => P)(compose: (P, P) => P): P
}
