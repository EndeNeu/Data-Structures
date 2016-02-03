package com.ebusiello.fds.queues.deque

import com.ebusiello.fds.queues.GenericQueue
import com.ebusiello.fds.tree.generic.queue.DoubleEndedQueue

/**
 * Deque (Double-ended queue) is a particular type of queue which allows operations on both tail and head of the queue.
 * It holds a reference to the first inserted element.
 *
 * |<- prepend 4           2 append -> |
 * |                                   |
 * [4,3,5,6,5,3,24,5,6,7,3,4,5,6,3,2]
 * |                                   |
 * | -> popLast 4         2 popFirst <-|
 *
 */
final class Deque[T](val head: DequeNode[T]) extends GenericQueue[T, Deque, DequeNode] with DoubleEndedQueue[T, Deque] {

  val tail: DequeNode[T] = new EmptyDequeNode[T]

  /**
   * Add an element on the head of the queue
   */
  override def prepend(mValue: T): Deque[T] =
    new Deque[T](new DequeNode[T](mValue, head))

  override def top: T =
    head.value

  override def pop: Deque[T] =
    new Deque[T](head.next)

  /**
   * Add an element on the tail of the queue
   */
  override def append(mValue: T): Deque[T] = ???

  /**
   * Remove the last element.
   */
  override def popLast(): Deque[T] = ???

  /**
   * Get last element.
   */
  override def last(): T = ???

}
