package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait DeletableTree[T, S[_]] extends Tree[T] {
  def delete(mValue: T)(implicit ord: Ordering[T]): S[T]
}