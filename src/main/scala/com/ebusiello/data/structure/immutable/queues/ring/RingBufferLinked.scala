package com.ebusiello.data.structure.immutable.queues.ring

import com.ebusiello.data.structure.immutable.queues.GenericLinkedQeueue

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