package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait RemoveableTree[T, S[_]] {
  def remove(v: T): S[T]
}
