package com.ebusiello.data.structure.immutable.queues.ring

import com.ebusiello.data.structure.immutable.queues.GenericLinkedQeueue

/**
 * RingBuffer is virtual, the nodes are a linked list where the last node points to an empty one
 * like in any normal queue, cyclicity is enforced by the queue which keeps track of the list length,
 * if the maximum length is reached drop the head and point to the second element.
 */
final class RingBuffer[T](size: Int, val head: RingBufferNode[T] = new EmptyRingBufferNode[T]) extends GenericLinkedQeueue[T, RingBuffer, RingBufferNode] {

  /**
   * Disadvantages of using a linked list, to know if the ring has reached
   * its full size we need to traverse the list and get the length, plus the
   * last node in the list doesn't have a pointer to the first node,
   * it simply points to an empty node so the check on the ring size is implemented
   * here.
   *
   * TODO insert is O(N) because we need the length of the list.
   * TODO maybe avoid using a linked list?
   */
  override def append(value: T): RingBuffer[T] =
    if (head.length() == size) new RingBuffer[T](size, head.next.append(value)) // if we reached the tail sip the head.
    else new RingBuffer[T](size, head.append(value))

  /**
   * Dequeue always removes the first inserted element which in our case is the head.
   */
  override def pop: RingBuffer[T] =
    new RingBuffer[T](size, head.next)

  def last: Option[T] =
    head.last

  override def isEmpty: Boolean =
    head.isEmpty

  override def top: Option[T] = head match {
    case e: EmptyRingBufferNode[T] => None
    case n: RingBufferNode[T] => Some(n.value)
  }

}