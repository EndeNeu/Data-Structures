package com.ebusiello.fds.queues.deque

import com.ebusiello.fds.queues.QueueException

final class EmptyDequeNode[T] extends DequeNode[T](null.asInstanceOf[T], null) {

  override def isEmpty: Boolean =
    true

  override def append(mValue: T): DequeNode[T] =
    new DequeNode[T](mValue, new EmptyDequeNode[T])

  override def popLast: DequeNode[T] =
    throw new QueueException("pop on empty node")

  override def toString: String =
    "E"
}