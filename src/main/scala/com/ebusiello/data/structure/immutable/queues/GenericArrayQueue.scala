package com.ebusiello.data.structure.immutable.queues

trait GenericArrayQueue[T, S[T] <: GenericArrayQueue[T, S]] {

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): S[T]

  def length(): Int

  def stringify(): String

  def isEmpty: Boolean

  def pop: S[T]

  def top: Option[T]

}
