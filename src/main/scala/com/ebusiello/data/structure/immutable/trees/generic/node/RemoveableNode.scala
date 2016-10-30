package com.ebusiello.data.structure.immutable.trees.generic.node

import scala.language.higherKinds

trait RemoveableNode[T, S[_]] extends Node[T] {
  def remove(v: T): S[T]
}