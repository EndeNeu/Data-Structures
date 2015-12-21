package com.ebusiello.fds.tree.generic.node

import scala.language.higherKinds

trait OrderableNode[T, S[_]] extends Node[T] {
  def insert(newValue: T)(implicit ord: Ordering[T]): S[T]
  def find(mValue: T)(implicit ord: Ordering[T]): Boolean
}
