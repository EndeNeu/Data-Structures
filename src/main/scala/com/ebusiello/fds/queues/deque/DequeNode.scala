package com.ebusiello.fds.queues.deque

import com.ebusiello.fds.queues.GenericQueueNode

class DequeNode[T](val value: T, val pointer: DequeNode[T]) extends GenericQueueNode[T, DequeNode] {

  override def isEmpty: Boolean =
    false

  /**
   * Adds an item onto the end of the queue.
   */
  override def enqueue(mValue: T): DequeNode[T] =
    new DequeNode[T](value, pointer.enqueue(mValue))

  /*def last: T = previous match {
    case e: EmptyDequeNode[T] => value
    case _ => previous.last
  }*/

  /**
   * Append is propagated until we find the empty node.
   */
  def append(mValue: T): DequeNode[T] =
    new DequeNode[T](value, pointer.append(mValue))

  /**
   * if the pointer points to an empty node it means this is the last node, pop it and return an empty node;
   * if the next node pointer has an empty node, this is the second to last node, return this with a pointer to an empty node
   * otherwise propagate the operation to the next node.
   */
  def popLast: DequeNode[T] = pointer match {
    case e: EmptyDequeNode[T] => e
    case n: DequeNode[T] => n.pointer match {
      case e: EmptyDequeNode[T] => new DequeNode[T](value, new EmptyDequeNode[T])
      case n: DequeNode[T] => new DequeNode[T](value, n.popLast)
    }
  }

}
