package com.ebusiello.fds.stacks

import scala.language.higherKinds

private[stacks] trait GenericStack[T, S[_]] {

  def push(mValue: T): S[T]

  def top: T

  def pop: S[T]

  def isEmpty: Boolean

}
