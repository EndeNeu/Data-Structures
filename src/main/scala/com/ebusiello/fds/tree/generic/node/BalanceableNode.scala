package com.ebusiello.fds.tree.generic.node

import scala.language.higherKinds

trait BalanceableNode[T, S[_]] {
  def leftRelativeDepth: Int
  def rightRelativeDepth: Int
  def rebalance()(implicit ord: Ordering[T]): S[T]
}
