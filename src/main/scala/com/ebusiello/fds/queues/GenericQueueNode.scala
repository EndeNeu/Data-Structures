package com.ebusiello.fds.queues

import scala.language.higherKinds

private[queues] trait GenericQueueNode[T, S[T] <: GenericQueueNode[T, S]] {

  /**
   * The value held by this node.
   */
  val value: T

  /**
   * Pointer to the previous node.
   */
  val next: S[T]

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): S[T]

  def isEmpty: Boolean

  def size(): Int =
    1 + next.size()

  def stringify: String = {
    s"${value.toString}, ${next.stringify}"
  }

}
