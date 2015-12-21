package com.ebusiello.fds.tree.generic.node

trait FindableNode[T] {

  def find(value: T)(implicit ord: Ordering[T]): Boolean

}
