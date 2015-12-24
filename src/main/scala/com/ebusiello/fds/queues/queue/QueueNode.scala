package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.GenericQueueNode

/**
 * A node hold a value and a reference to the previous node in the queue.
 */
class QueueNode[T](val value: T, val pointer: QueueNode[T]) extends GenericQueueNode[T, QueueNode] {

  override def enqueue(mValue: T): QueueNode[T] =
    new QueueNode[T](value, previous.enqueue(mValue))

  override def isEmpty: Boolean =
    false

  override def size(): Int =
    1 + pointer.size()
}
