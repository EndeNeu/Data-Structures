package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.GenericQueueNode

/**
 * A node hold a value and a reference to the previous node in the queue.
 */
class QueueNode[T](val value: T, val pointer: QueueNode[T]) extends GenericQueueNode[T, QueueNode] {

  /**
   * We dequeue a node checking the next two node relative to this node.
   *
   * 1) if the nex it's empty means that the com.ebusiello.fds.stack has only one node and popping should return an empty com.ebusiello.fds.stack:
   *
   * [1] -> [E] ---> dequeue ---> [E]
   *
   * 2) if the next is not empty, but the next.next is, remove the immediate next node:
   *
   * [1] -> [2] -> [E]  ---> dequeue ---> [1] -> [E]
   *
   * 3) if the next.next is not empty, delegate the pop to the next node.
   *
   * [1] -> [2] -> [3] -> [E] ---> dequeue ---> [1] -> [2] -> [E]
   *
   *
   */
  override def dequeue: QueueNode[T] = previous match {
    case e: EmptyQueueNode[T] => e
    case nonEmpty: QueueNode[T] => nonEmpty.previous match {
      case empty: EmptyQueueNode[T] => new QueueNode[T](value, new EmptyQueueNode[T])
      case _ => new QueueNode[T](value, nonEmpty.dequeue)
    }
  }

  /**
   * Alias for the previous element.
   * @return
   */
  override def previous: QueueNode[T] =
    pointer

  /**
   *
   */
  override def enqueue(mValue: T): QueueNode[T] =
    new QueueNode[T](value, previous.enqueue(mValue))

  override def isEmpty: Boolean =
    false

  override def size(): Int =
    1 + pointer.size()
}
