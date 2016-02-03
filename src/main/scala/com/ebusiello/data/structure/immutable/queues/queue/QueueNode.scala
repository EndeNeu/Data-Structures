package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.GenericQueueNode

/**
 * A node hold a value and a reference to the previous node in the queue.
 */
class QueueNode[T](val value: T, val next: QueueNode[T]) extends GenericQueueNode[T, QueueNode] {

  /**
   * Adds an item onto the end of the queue.
   */
  override def append(mValue: T): QueueNode[T] =
    new QueueNode[T](value, next.append(mValue))

  override def isEmpty: Boolean =
    false

}
