package com.ebusiello.fds.queues

import scala.language.higherKinds

private[queues] trait GenericQueueNode[T, S[_]] {

  /**
   * Pointer to the previous node.
   */
  val pointer: S[T]

  def previous: S[T] =
    pointer

  /**
   * Adds an item onto the end of the queue.
   */
  def enqueue(mValue: T): S[T]

  def isEmpty: Boolean

  def size(): Int

}
