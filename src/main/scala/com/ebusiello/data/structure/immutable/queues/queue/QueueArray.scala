package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.{QueueException, GenericArrayQueue}

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
final class QueueArray[T: ClassTag] private(val queue: Array[T], private val size: Int, private val currentSize: Int, val frontPointer: Int) extends GenericArrayQueue[T, QueueArray] {

  def this(size: Int) = this(new Array[T](size), size, 0, 0)

  /**
    * Adds an item onto the end of the queue.
    *
    * use the modulo of the pointer plus the size to calculate the index to update, it works in a circular fashion:
    *
    *  [1, 2, E, E] size = 2
    *   ^
    *  pointer
    *
    *  append(3) => (pointer + size) % 4 = 2
    *
    *  [1, 2, 3, E] size = 3
    *   ^
    * pointer
    *
    *
    *
    *  [E, 2, 3, 4] size = 3
    *      ^
    *      pointer
    *
    *  append(5) => (1 + 3) % 4 = 0
    */
  def append(mValue: T): QueueArray[T] = {
    if (isFull) throw new QueueException("Queue is full.")
    else new QueueArray[T](updateQueue((frontPointer + currentSize) % size, mValue), size, currentSize + 1, frontPointer)
  }

  /**
    * Removes the item from the front of the queue.
    *
    * if the pointer is at the last element of the array it works like a ring and the index 0 is updated
    * else the pointer is simply moved forward to the next element.
    */
  def pop: QueueArray[T] = {
    if (isEmpty) throw new QueueException("Pop on empty queue.")
    else if (frontPointer == size - 1) new QueueArray[T](queue, size, currentSize - 1, 0)
    else new QueueArray[T](queue, size, currentSize - 1, frontPointer + 1)
  }

  /**
    * Returns the item at the front of the queue.
    */
  def top: Option[T] =
    if (isEmpty) None
    else Some(queue(frontPointer))

  def length(): Int =
    currentSize

  def stringify(): String =
    if (isEmpty) "E | T"
    else s"${_stringify()}| T"

  private def _stringify(): String = {
    if (isEmpty) "E "
    else s"${pop._stringify()}|${top.get}"
  }

  def isEmpty: Boolean =
    currentSize == 0

  def isFull: Boolean =
    currentSize == size

  /**
    * Helper kestrel combinator.
    */
  private def updateQueue(index: Int, el: T): Array[T] = {
    queue.update(index, el)
    queue
  }

}
