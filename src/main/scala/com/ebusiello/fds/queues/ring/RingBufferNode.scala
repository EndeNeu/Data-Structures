package com.ebusiello.fds.queues.ring

import com.ebusiello.fds.queues.GenericQueueNode

class RingBufferNode[T](val value: T, val next: RingBufferNode[T]) extends GenericQueueNode[T, RingBufferNode] {

  override def append(mValue: T): RingBufferNode[T] =
    new RingBufferNode[T](value, next.append(mValue))

  override def isEmpty: Boolean =
    false

  def last: T = next match {
    case e: EmptyRingBufferNode[T] => value
    case _ => next.last
  }

  def length(): Int =
    1 + next.length()

  /**
   * Removes the first entered element.
   */
  override def pop(): RingBufferNode[T] = ???

  /**
   * returns the first entered element
   */
  override def top(): T = ???
}
