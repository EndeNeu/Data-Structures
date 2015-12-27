package com.ebusiello.fds.queues.deque

import com.ebusiello.fds.queues.queue.DoubleEndedQueue
import com.ebusiello.fds.queues.{ GenericQueue, QueueException }

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
final class Deque[T](val head: DequeNode[T], val root: DequeNode[T]) extends GenericQueue[T, Deque, DequeNode] with DoubleEndedQueue[T, DequeNode] {

  /**
   * Add an element to the end of the queue
   */
  /*
  override def append(mValue: T): Deque[T] =
    new Deque[T](head.append(mValue))

  /**
   * Remove the latest added element (end of the queue)
   */
  override def popLast: Deque[T] =
    new Deque[T](head.popLast)

  /**
   * Add an element to the top of the queue which becomes the head of a new Deque
   */
  override def prepend(mValue: T): Deque[T] =
    new Deque[T](new DequeNode[T](mValue, head))

  /**
   * Remove the head of the com.ebusiello.fds.queue, the second inserted element becomes the head of the com.ebusiello.fds.queue.
   */
  override def popFirst: Deque[T] =
    new Deque[T](head.previous)

  override def last: T =
    head.last

  override def isEmpty: Boolean =
    head.isEmpty

  override def first: T = head match {
    case e: EmptyDequeNode[T] => throw new QueueException("first on empty queue.")
    case n: DequeNode[T] => n.value
  }
  */
  override def enqueue(mValue: T): Deque[T] =
    new Deque[T](head, root.enqueue(mValue))

  override def dequeue: Deque[T] = ???

  override def top: T = ???
}