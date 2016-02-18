package com.ebusiello.data.structure.immutable.queues

import scala.language.higherKinds

private[queues] trait GenericLinkedQueueNode[T, S[T] <: GenericLinkedQueueNode[T, S]] {

  /**
   * The value held by this node.
   */
  val value: T

  /**
   * Pointer to the previous node.
   */
  val next: S[T]

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): S[T]

  def top: Option[T]

  def pop: S[T]

  def isEmpty: Boolean

  def size(): Int =
    1 + next.size()

  def stringify: String = {
    s"${value.toString}, ${next.stringify}"
  }

}
