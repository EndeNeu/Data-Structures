package com.ebusiello.fds.tree.generic.node

import scala.language.higherKinds

trait RotableNode[T, S[_]] {
  protected def rotateNode(): S[T]
}
