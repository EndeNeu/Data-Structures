package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.GenericLinkedQueueNode

/**
 * A node hold a value and a reference to the previous node in the queue.
 */
class QueueLinkedNode[T](val value: T, val next: QueueLinkedNode[T]) extends GenericLinkedQueueNode[T, QueueLinkedNode] {

  /**
   * Adds an item onto the end of the queue.
   */
  override def append(mValue: T): QueueLinkedNode[T] =
    new QueueLinkedNode[T](value, next.append(mValue))

  override def isEmpty: Boolean =
    false

  override def top: Option[T] =
    if (next.isEmpty) Some(value)
    else next.top

  override def pop: QueueLinkedNode[T] =
    if (next.isEmpty) new EmptyQueueLinkedNode[T]
    else new QueueLinkedNode[T](value, next.pop)
}
