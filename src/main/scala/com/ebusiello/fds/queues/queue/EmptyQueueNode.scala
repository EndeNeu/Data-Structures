package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.QueueException

final class EmptyQueueNode[T]() extends QueueNode[T](null.asInstanceOf[T], null) {

  override def isEmpty: Boolean =
    true

  override def previous: QueueNode[T] =
    throw new QueueException("Previous on empty node.")

  override def enqueue(mValue: T): QueueNode[T] =
    new QueueNode[T](mValue, new EmptyQueueNode[T])

  override def size(): Int = 0
}
