package com.ebusiello.data.structure.immutable.queues.ring

import com.ebusiello.data.structure.immutable.queues.{GenericLinkedQueueNode, GenericLinkedQeueue}

/**
 * RingBuffer is virtual, the nodes are a linked list where the last node points to an empty one
 * like in any normal queue, cyclicity is enforced by the queue which keeps track of the list length,
 * if the maximum length is reached drop the head and point to the second element.
 */
final class RingBufferLinked[T] private (size: Int, val head: RingBufferNode[T], currentLength: Int) extends GenericLinkedQeueue[T, RingBufferLinked, RingBufferNode] {

  def this() = this(0, new EmptyRingBufferNode[T], 0)
  def this(size: Int) = this(size, new EmptyRingBufferNode[T], 0)

  /**
   * The last node in the list doesn't have a pointer to the first node,
   * it simply points to an empty node so the check on the ring size is implemented here.
   */
  override def append(value: T): RingBufferLinked[T] =
    if (currentLength == size) new RingBufferLinked[T](size, head.next.append(value), currentLength) // if we reached the tail skip the head.
    else new RingBufferLinked[T](size, head.append(value), currentLength + 1)

  /**
   * Dequeue always removes the first inserted element which in our case is the head.
   */
  override def pop: RingBufferLinked[T] =
    new RingBufferLinked[T](size, head.next, currentLength - 1)

  def last: Option[T] =
    head.last

  override def isEmpty: Boolean =
    head.isEmpty

  override def top: Option[T] = head match {
    case e: EmptyRingBufferNode[T] => None
    case n: RingBufferNode[T] => Some(n.value)
  }

}

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

private[ring] final class EmptyRingBufferNode[T] extends RingBufferNode[T](null.asInstanceOf[T], null) {

  override def append(mValue: T): RingBufferNode[T] =
    new RingBufferNode[T](mValue, new EmptyRingBufferNode[T])

  override def isEmpty: Boolean =
    true

  override def last: Option[T] =
    None

  override def length(): Int =
    0

  override def stringify: String =
    "E"

  override def top: Option[T] =
    None

  override def pop: RingBufferNode[T] =
    this
}