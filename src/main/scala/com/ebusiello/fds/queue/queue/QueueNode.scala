package com.ebusiello.fds.queue.queue

import com.ebusiello.fds.queue.queue.AbstractQueueNode

/**
 * A node hold a value and a reference to the previous node in the queue.
 */
final class QueueNode[T](val value: T, pointer: AbstractQueueNode[T]) extends AbstractQueueNode[T] {

  /**
   * We dequeue a node checking the next two node relative to this node.
   *
   * 1) if the nex it's empty means that the stack has only one node and popping should return an empty stack:
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
  override def dequeue: AbstractQueueNode[T] = previous match {
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
  override def previous: AbstractQueueNode[T] =
    pointer

  /**
   *
   */
  override def enqueue(mValue: T): AbstractQueueNode[T] =
    new QueueNode[T](value, previous.enqueue(mValue))


  override def isEmpty: Boolean =
    false

}
