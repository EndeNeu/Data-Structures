package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.GenericLinkedQeueue

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
 * The queue is actually reversed, it holds a reference to the first inserted element of the queue
 * and every node has a reference to subsequently inserted elements. This allows
 * o(N) inserts but you get back o(1) pop and top.
 */
final class ReversedQueueLinkedList[T] private (val head: QueueLinkedNode[T]) extends GenericLinkedQeueue[T, ReversedQueueLinkedList, QueueLinkedNode] {

  def this() = this(new EmptyQueueLinkedNode[T])

  /**
   * Adds an item onto the tail of the queue.
   */
  override def append(mValue: T): ReversedQueueLinkedList[T] =
    new ReversedQueueLinkedList[T](head.append(mValue))

  /**
   * Removes the item from the front of the queue.
   */
  override def pop: ReversedQueueLinkedList[T] =
    new ReversedQueueLinkedList[T](head.next)

  /**
   * Returns the item at the front of the queue.
   */
  override def top: Option[T] = head match {
    case e: EmptyQueueLinkedNode[T] => None
    case n: QueueLinkedNode[T] => Some(head.value)
  }

}

object ReversedQueueLinkedList {
  def apply[T](value: T): ReversedQueueLinkedList[T] =
    new ReversedQueueLinkedList[T](new QueueLinkedNode[T](value, new EmptyQueueLinkedNode[T]))
}