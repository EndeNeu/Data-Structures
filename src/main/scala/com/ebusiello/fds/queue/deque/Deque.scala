package com.ebusiello.fds.queue.deque

import com.ebusiello.fds.queue.queue.QueueException

/**
 * Deque (Double-ended queue) is a particular type of queue which allows operations on both tail and head of the queue.
 * It holds a reference to the first inserted element.
 *
 *   |<- prepend 4           2 append -> |
 *   |                                   |
 *    [4,3,5,6,5,3,24,5,6,7,3,4,5,6,3,2]
 *   |                                   |
 *   | -> popLast 4         2 popFirst <-|
 *
 */
final class Deque[T](head: AbstractDequeNode[T] = new EmptyDequeNode[T]) extends AbstractDeque[T] {

  /**
   * Add an element to the end of the queue
   */
  override def append(mValue: T): AbstractDeque[T] =
    new Deque[T](head.append(mValue))

  /**
   * Remove the latest added element (end of the queue)
   */
  override def popLast: AbstractDeque[T] =
    new Deque[T](head.popLast)

  /**
   * Add an element to the top of the queue which becomes the head of a new Deque
   */
  override def prepend(mValue: T): AbstractDeque[T] =
    new Deque[T](new DequeNode[T](mValue, head))

  /**
   * Remove the head of the queue, the second inserted element becomes the head of the queue.
   */
  override def popFirst: AbstractDeque[T] =
    new Deque[T](head.previous)

  override def last: T =
    head.last

  override def isEmpty: Boolean =
    head.isEmpty

  override def first: T = head match {
    case e: EmptyDequeNode[T] => throw new QueueException("first on empty queue.")
    case n: DequeNode[T] => n.value
  }

}