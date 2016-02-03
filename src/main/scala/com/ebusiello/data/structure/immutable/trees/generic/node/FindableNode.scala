package com.ebusiello.data.structure.immutable.trees.generic.node

trait FindableNode[T] {

  def find(value: T)(implicit ord: Ordering[T]): Boolean

}
