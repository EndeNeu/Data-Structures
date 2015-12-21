package com.ebusiello.fds.tree.generic.tree

import scala.language.higherKinds

trait MappableTree[T, S[_]] {
  def map[V](f: (T) => V): S[V]
}
