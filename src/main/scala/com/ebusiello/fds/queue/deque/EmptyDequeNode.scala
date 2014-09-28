package com.ebusiello.fds.queue.deque

import com.ebusiello.fds.queue.queue.QueueException

final class EmptyDequeNode[T] extends AbstractDequeNode[T] {

  override def isEmpty: Boolean =
    true

  override def last: T =
    throw new QueueException("last on empty node.")

  override def append(mValue: T): AbstractDequeNode[T] =
    new DequeNode[T](mValue, new EmptyDequeNode[T])

  override def previous: AbstractDequeNode[T] =
    this

  override def popLast: AbstractDequeNode[T] =
    throw new QueueException("pop on empty node")
}
