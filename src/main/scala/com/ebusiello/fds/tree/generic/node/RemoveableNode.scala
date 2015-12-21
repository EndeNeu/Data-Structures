package com.ebusiello.fds.tree.generic.node

import scala.language.higherKinds

trait RemoveableNode[T, S[_]] {
  def remove(v: T): S[T]
}