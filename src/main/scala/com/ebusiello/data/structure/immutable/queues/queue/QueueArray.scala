package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.GenericArrayQueue

import scala.reflect.ClassTag

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
 */
final class QueueArray[T: ClassTag] private (private val queue: Array[T], private val queueSize: Int) extends GenericArrayQueue[T, QueueArray] {

  def this() = this(Array.empty[T], 0)

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): QueueArray[T] = {
    new QueueArray[T](mValue +: queue, queueSize + 1)
  }

  /**
   * Returns the item at the front of the queue.
   */
  def top: Option[T] =
    if (isEmpty) None
    else Some(queue(queueSize - 1))

  /**
   * Removes the item from the front of the queue.
   */
  def pop: QueueArray[T] = {
    if (isEmpty) this
    else new QueueArray[T](queue.init, queueSize - 1)
  }

  def length(): Int =
    queueSize

  def stringify(): String =
    if (isEmpty) "E | T"
    else queue.mkString("E |", "|", "| T")

  def isEmpty: Boolean =
    queueSize == 0

}
