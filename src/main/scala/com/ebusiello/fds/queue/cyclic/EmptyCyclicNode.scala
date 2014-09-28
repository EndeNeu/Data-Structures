package com.ebusiello.fds.queue.cyclic

import com.ebusiello.fds.queue.queue.QueueException

final class EmptyCyclicNode[T] extends AbstractCyclicNode[T] {

  override def previous: AbstractCyclicNode[T] =
    this

  override def enqueue(mValue: T): AbstractCyclicNode[T] =
    new CyclicNode[T](mValue, new EmptyCyclicNode[T])

  override def isEmpty: Boolean =
    true

  override def last: T =
    throw new QueueException("last on empty queue.")

  override def length(accumulator: Int): Int =
    accumulator
}
