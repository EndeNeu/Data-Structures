package com.ebusiello.fds.stacks

import scala.language.higherKinds

private[stacks] trait GenericStackNode[T, S[_]] {

  def previous: S[T]

  def isEmpty: Boolean

}
