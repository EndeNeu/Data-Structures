package com.ebusiello.data.structure.mutable.stacks

private[structure] trait GenericMutableStack[T] {

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

  def push(mValue: T): Unit

}

