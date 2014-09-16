package com.ebusiello.fds.tree

import scala.language.higherKinds

trait Tree

trait GenericTree extends Tree {

}

trait SortableTree[T, S[_]] {
  def sort(implicit ord: Ordering[T]): S[T]
}


