package com.ebusiello.data.structure.mutable.queues

import scala.reflect.ClassTag

/**
 * To avoid shifting the elements in the array the elements are appended to the array
 * so the head of the array is the first element inserted in the queue
 * while the last of the array is the last one inserted.
 */
final class MutableQueueArray[T: ClassTag]() extends GenericMutableQueue[T] {

  private val queue = scala.collection.mutable.ArrayBuffer.empty[T]
  private var queueSize = 0

  /**
   * Adds an item onto the end of the queue.
   */
  override def append(mValue: T): Unit = {
    queueSize += 1
    queue += mValue
  }

  override def size(): Int =
    queueSize

  /**
   * Returns the item at the front of the queue.
   */
  override def top: Option[T] =
    if (queueSize == 0) None
    else Some(queue(0))

  override def stringify(): String =
    if (queueSize == 0) "E | T"
    else queue.mkString("E |", "|", "| T")

  override def isEmpty: Boolean =
    queueSize == 0

  /**
   * Removes the item from the front of the queue.
   */
  override def pop: Option[T] = {
    if (queueSize == 0) None
    else {
      queueSize -= 1
      Some(queue.remove(0))
    }
  }
}