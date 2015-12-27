package com.ebusiello.fds.queues

import scala.language.higherKinds

private[queues] trait GenericQueueNode[T, S[T] <: GenericQueueNode[T, S]] {

  /**
   * Pointer to the previous node.
   */
  val pointer: S[T]

  /**
   * Adds an item onto the end of the queue.
   */
  def enqueue(mValue: T): S[T]

  def isEmpty: Boolean

  def size(): Int =
    1 + pointer.size()

}
