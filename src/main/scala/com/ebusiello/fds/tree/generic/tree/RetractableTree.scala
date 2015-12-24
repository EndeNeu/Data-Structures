package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait RetractableTree[T, S[_]] {
  def rebalance()(implicit ord: Ordering[T]): S[T]
}
