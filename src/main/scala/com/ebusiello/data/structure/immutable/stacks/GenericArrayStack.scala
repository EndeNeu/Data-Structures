package com.ebusiello.data.structure.immutable.stacks

import scala.language.higherKinds

private[stacks] trait GenericArrayStack[T, S[T] <: GenericArrayStack[T, S]] {

  def push(mValue: T): S[T]

  def top: Option[T]

  def pop: S[T]

  def isEmpty: Boolean

  def nonEmpty: Boolean =
    !isEmpty
}
