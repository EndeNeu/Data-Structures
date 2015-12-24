package com.ebusiello.fds.queues

import scala.language.higherKinds

private[queues] trait GenericQueueNode[T, S[_]] {

  val pointer: S[T]

  def previous: S[T] =
    pointer

  def dequeue: S[T]

  def enqueue(mValue: T): S[T]

  def isEmpty: Boolean

  def size(): Int

}
