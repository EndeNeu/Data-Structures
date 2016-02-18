package com.ebusiello.data.structure.immutable.stacks

import scala.language.higherKinds

private[stacks] trait GenericStackLinkedNode[T, S[_]] {

  def previous: S[T]

  def isEmpty: Boolean

}
