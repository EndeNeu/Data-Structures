package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait RemoveableTree[T, S[_]] {
  def remove(v: T)(implicit ord: Ordering[T]): S[T]
}
