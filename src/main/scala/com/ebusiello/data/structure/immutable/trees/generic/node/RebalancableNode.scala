package com.ebusiello.data.structure.immutable.trees.generic.node

import scala.language.higherKinds

trait RebalancableNode[T, S[_]] {
  def rebalance()(implicit ord: Ordering[T]): S[T]
}
