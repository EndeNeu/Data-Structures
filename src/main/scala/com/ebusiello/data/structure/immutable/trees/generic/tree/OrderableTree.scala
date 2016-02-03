package com.ebusiello.data.structure.immutable.trees.generic.tree

import scala.language.higherKinds

trait OrderableTree[T, S[_]] {
  def insert(value: T)(implicit ord: Ordering[T]): S[T]
  def find(value: T)(implicit ord: Ordering[T]): Boolean
  def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): S[V]
}
