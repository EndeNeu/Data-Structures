package com.ebusiello.data.structure.immutable.trees.generic.node

import scala.language.higherKinds

trait BalanceableNode[T, S[_]] extends Node[T] {
  def resort()(implicit ord: Ordering[T]): S[T]
}
