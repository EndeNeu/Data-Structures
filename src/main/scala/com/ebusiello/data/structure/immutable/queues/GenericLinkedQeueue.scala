package com.ebusiello.data.structure.immutable.queues

import com.ebusiello.data.structure.generic.GenericQueue

import scala.reflect.ClassTag

private[queues] trait GenericLinkedQeueue[T, S[_], N[T] <: GenericQueueNode[T, N]] extends GenericQueue[T, S] {

  val head: N[T]

  def isEmpty: Boolean =
    head.isEmpty

  def size(): Int =
    head.size()

  def stringify()(implicit ev: ClassTag[S[_]]): String = {
    s"${ev.runtimeClass}(${head.stringify})"
  }
}