package com.ebusiello.fds.queues

import scala.language.higherKinds
import scala.reflect.{ ClassTag, classTag }

private[queues] trait GenericQueue[T, S[_], N[T] <: GenericQueueNode[T, N]] {

  val head: N[T]

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
  def top: T

  def isEmpty: Boolean =
    head.isEmpty

  def size(): Int =
    head.size()

  def stringify()(implicit ev: ClassTag[S[_]]): String = {
    s"${ev.runtimeClass}(${head.stringify})"
  }
}
