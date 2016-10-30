package com.ebusiello.data.structure.immutable.trees.generic.node

import scala.language.higherKinds

trait RotableNode[T, S[_]] extends Node[T]  {
  protected def rotateNode(): S[T]
}
