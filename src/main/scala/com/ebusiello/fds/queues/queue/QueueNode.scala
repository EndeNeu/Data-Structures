package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.GenericQueueNode

/**
 * A node hold a value and a reference to the previous node in the queue.
 */
class QueueNode[T](val value: T, val pointer: QueueNode[T]) extends GenericQueueNode[T, QueueNode] {

  /**
   * Adds an item onto the end of the queue.
   */
  override def append(mValue: T): QueueNode[T] =
    new QueueNode[T](value, pointer.append(mValue))

  override def isEmpty: Boolean =
    false

  /**
   * Removes the first entered element.
   */
  override def pop(): QueueNode[T] =
    if (pointer.isEmpty) new EmptyQueueNode[T]
    else new QueueNode[T](value, pointer.pop())

  /**
   * returns the first entered element
   */
  override def top(): T =
    if (pointer.isEmpty) value
    else pointer.top()

}
