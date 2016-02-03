package com.ebusiello.fds.queues.ring

import com.ebusiello.fds.queues.GenericQueueNode

class RingBufferNode[T](val value: T, val pointer: RingBufferNode[T]) extends GenericQueueNode[T, RingBufferNode] {

  override def append(mValue: T): RingBufferNode[T] =
    new RingBufferNode[T](value, pointer.append(mValue))

  override def isEmpty: Boolean =
    false

  def last: T = pointer match {
    case e: EmptyRingBufferNode[T] => value
    case _ => pointer.last
  }

  def length(): Int =
    1 + pointer.length()

  /**
   * Removes the first entered element.
   */
  override def pop(): RingBufferNode[T] = ???

  /**
   * returns the first entered element
   */
  override def top(): T = ???
}
