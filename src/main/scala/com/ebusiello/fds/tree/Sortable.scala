package com.ebusiello.fds.tree

import scala.language.higherKinds

trait Sortable[T, S[_]] {
  def sort(direction: Direction)(implicit ord: Ordering[T]): S[T]
}

sealed abstract class Direction(val desc: Boolean) {
  def reverse: Direction
}

case object ASC extends Direction(false) {
  def reverse = DESC
}

case object DESC extends Direction(true) {
  def reverse = ASC
}