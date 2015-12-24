package com.ebusiello.fds.tree.generic.node

import scala.language.higherKinds

trait RetractableNode[T, S[_]] {
  def rebalance()(implicit ord: Ordering[T]): S[T]
}
