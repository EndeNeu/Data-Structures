package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.GenericLinkedQeueue

/**
 * FIFO
 *
 * Enqueue
 * val 1
 * |     back           front
 * |->  [1,2,5,2,6,5,3,2] -> |
 * |-> val 2
 * Dequeue
 *
 * The queue is not reversed, it holds a reference to the last inserted element of the queue and every node has a
 * reference to the next node, this allows o(1) inserts but you get back o(N) pop and top.
 */
final class QueueLinkedList[T](val head: QueueLinkedNode[T] = new EmptyQueueLinkedNode[T]) extends GenericLinkedQeueue[T, QueueLinkedList, QueueLinkedNode] {

  /**
   * Adds an item onto the tail of the queue.
   */
  override def append(mValue: T): QueueLinkedList[T] =
    new QueueLinkedList[T](new QueueLinkedNode[T](mValue, head))

  /**
   * Removes the item from the front of the queue.
   */
  override def pop: QueueLinkedList[T] =
    if (head.isEmpty) this
    else new QueueLinkedList[T](head.pop)

  /**
   * Returns the item at the front of the queue.
   */
  override def top: Option[T] = head match {
    case e: EmptyQueueLinkedNode[T] => None
    case n: QueueLinkedNode[T] => head.top
  }
}

object QueueLinkedList {
  def apply[T](value: T): QueueLinkedList[T] =
    new QueueLinkedList[T](new QueueLinkedNode[T](value, new EmptyQueueLinkedNode[T]))
}