package com.ebusiello.fds.queues.ring

import com.ebusiello.fds.queues.QueueException

final class EmptyRingBufferNode[T] extends RingBufferNode[T](null.asInstanceOf[T], null) {

  override def append(mValue: T): RingBufferNode[T] =
    new RingBufferNode[T](mValue, new EmptyRingBufferNode[T])

  override def isEmpty: Boolean =
    true

  override def last: T =
    throw new QueueException("last on empty queue.")

  override def length(): Int =
    0

  override def stringify: String =
    "E"
}

