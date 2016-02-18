package com.ebusiello.data.structure.mutable.queues

private[structure] trait GenericMutableQueue[T] {

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): Unit

  /**
   * Removes the item from the front of the queue.
   */
  def pop: Option[T]

  /**
   * Returns the item at the front of the queue.
   */
  def top: Option[T]

  def isEmpty: Boolean

  def size(): Int

  def stringify(): String

}
