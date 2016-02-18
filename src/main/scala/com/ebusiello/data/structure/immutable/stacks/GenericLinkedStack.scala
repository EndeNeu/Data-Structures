package com.ebusiello.data.structure.immutable.stacks

import scala.language.higherKinds

private[stacks] trait GenericLinkedStack[T, S[_]] {

  def push(mValue: T): S[T]

  def top: Option[T]

  def pop: S[T]

  def isEmpty: Boolean

}
