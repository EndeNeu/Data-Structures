package com.ebusiello.fds.stack

import scala.language.higherKinds

abstract class AbstractStack[T, S[_]] {

  def push(mValue: T): S[T]

  def top: T

  def pop: S[T]

  def isEmpty: Boolean

}