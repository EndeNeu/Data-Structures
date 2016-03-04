package com.ebusiello.data.structure.immutable.queues.ring

import com.ebusiello.data.structure.immutable.queues.GenericLinkedQueueNode

private[ring] class RingBufferNode[T](val value: T, val next: RingBufferNode[T]) extends GenericLinkedQueueNode[T, RingBufferNode] {

  override def append(mValue: T): RingBufferNode[T] =
    new RingBufferNode[T](value, next.append(mValue))

  override def isEmpty: Boolean =
    false

  def last: Option[T] = next match {
    case e: EmptyRingBufferNode[T] => Some(value)
    case _ => next.last
  }

  def length(): Int =
    1 + next.length()

  override def top: Option[T] = None

  override def pop: RingBufferNode[T] =
    if (next.isEmpty) new EmptyRingBufferNode[T]
    else next.pop
}
