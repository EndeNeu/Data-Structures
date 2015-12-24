package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait BalanceableTree[T, S[_]] {
  def resort()(implicit ord: Ordering[T]): S[T]
}
