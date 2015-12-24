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
 * The com.ebusiello.fds.queue holds a reference to the head of the com.ebusiello.fds.queue and every node has a reference to his previous node.
 */
final class Queue[T](val head: QueueNode[T] = new EmptyQueueNode[T]) extends GenericQueue[T, Queue, QueueNode] {

  /**
   * Adds an item onto the end of the queue.
   */
  override def enqueue(mValue: T): Queue[T] =
    new Queue[T](head.enqueue(mValue))

  /**
   * Removes the item from the front of the queue.
   */
  override def dequeue: Queue[T] =
    new Queue[T](head.previous)

  /**
   * Returns the item at the front of the queue.
   */
  override def top: T = head match {
    case e: EmptyQueueNode[T] => throw new QueueException("Top on empty queue.")
    case n: QueueNode[T] => n.value
  }

}

object Queue {

  def apply[T](value: T): Queue[T] =
    new Queue[T](new QueueNode[T](value, new EmptyQueueNode[T]))
}