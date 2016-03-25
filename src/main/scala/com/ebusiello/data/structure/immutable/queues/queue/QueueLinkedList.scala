package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.{QueueException, GenericLinkedQeueue, GenericLinkedQueueNode}

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
final class QueueLinkedList[T] private (val head: QueueLinkedNode[T]) extends GenericLinkedQeueue[T, QueueLinkedList, QueueLinkedNode] {

  def this() = this(new EmptyQueueLinkedNode[T])

  /**
   * Adds an item onto the tail of the queue.
   */
  override def append(mValue: T): QueueLinkedList[T] =
    new QueueLinkedList[T](new QueueLinkedNode[T](mValue, head))

  /**
   * Removes the item from the front of the queue.
   */
  override def pop: QueueLinkedList[T] =
    if (head.isEmpty) throw new QueueException("Pop on empty queue.")
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

/**
  * A node hold a value and a reference to the previous node in the queue.
  */
private[queue] class QueueLinkedNode[T](val value: T, val next: QueueLinkedNode[T]) extends GenericLinkedQueueNode[T, QueueLinkedNode] {

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

private[queue] final class EmptyQueueLinkedNode[T]() extends QueueLinkedNode[T](null.asInstanceOf[T], null) {

  override def isEmpty: Boolean =
    true

  override def append(mValue: T): QueueLinkedNode[T] =
    new QueueLinkedNode[T](mValue, new EmptyQueueLinkedNode[T])

  override def size(): Int = 0

  override def stringify: String =
    "E"
}
