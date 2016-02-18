package com.ebusiello.data.structure.immutable.queues

import scala.reflect.ClassTag

private[queues] trait GenericLinkedQeueue[T, S[_], N[T] <: GenericLinkedQueueNode[T, N]] {

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): S[T]

  /**
   * Removes the item from the front of the queue.
   */
  def pop: S[T]

  /**
   * Returns the item at the front of the queue.
   */
  def top: Option[T]

  val head: N[T]

  def isEmpty: Boolean =
    head.isEmpty

  def size(): Int =
    head.size()

  def stringify()(implicit ev: ClassTag[S[_]]): String = {
    s"${ev.runtimeClass}(${head.stringify})"
  }
}