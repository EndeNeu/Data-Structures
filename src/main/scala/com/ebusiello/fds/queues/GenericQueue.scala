package com.ebusiello.fds.queues

import scala.language.higherKinds

private[queues] trait GenericQueue[T, S[_], N[T] <: GenericQueueNode[T, N]] {

  val head: N[T]

  def enqueue(mValue: T): S[T]

  def dequeue: S[T]

  def top: T

  def isEmpty: Boolean =
    head.isEmpty

  def size(): Int =
    head.size()
}
