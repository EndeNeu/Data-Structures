package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.{ GenericQueue, QueueException }

/**
 * FIFO
 *
 * Enqueue
 *  val 1
 *   |     back           front
 *   |->  [1,2,5,2,6,5,3,2] -> |
 *                             |-> val 2
 *                                 Dequeue
 *
 * The queue is actually reversed, it holds a reference to the last element of the queue and every node has a reference to his previous node.
  * This allows o(1) inserts but you get back o(n) pop and top.
 */
final class Queue[T](val head: QueueNode[T] = new EmptyQueueNode[T]) extends GenericQueue[T, Queue, QueueNode] {

  /**
   * Adds an item onto the tail of the queue.
   */
  override def append(mValue: T): Queue[T] =
    new Queue[T](new QueueNode[T](mValue, head))

  /**
   * Removes the item from the front of the queue.
   */
  override def pop: Queue[T] =
    new Queue[T](head.pop())

  /**
   * Returns the item at the front of the queue.
   */
  override def top: T = head match {
    case e: EmptyQueueNode[T] => throw new QueueException("Top on empty queue.")
    case n: QueueNode[T] => head.top()
  }

}

object Queue {

  def apply[T](value: T): Queue[T] =
    new Queue[T](new QueueNode[T](value, new EmptyQueueNode[T]))
}