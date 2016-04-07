package com.ebusiello.data.structure.immutable.trees.generic.tree

import scala.language.higherKinds

trait DeletableTree[T, S[_]] {
  def delete(mValue: T)(implicit ord: Ordering[T]): S[T]
}