package com.ebusiello.data.structure.immutable.trees.generic.tree

import scala.language.higherKinds

trait RetractableTree[T, S[_]] {
  def rebalance()(implicit ord: Ordering[T]): S[T]
}
