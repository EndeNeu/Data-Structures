package com.ebusiello.fds.tree.generic.node

trait BalancedNode {
  def leftRelativeDepth: Int

  def rightRelativeDepth: Int
}
