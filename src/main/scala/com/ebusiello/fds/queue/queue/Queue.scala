package com.ebusiello.fds.queue.queue

import com.ebusiello.fds.queue.{QueueException, AbstractQueue}

final class Queue[T](private[this] val queue: List[T]) extends AbstractQueue[T, Queue] {

  override def enqueue(mValue: T): Queue[T] =
    new Queue[T](queue.+:(mValue))

  override def back: T =
    if (isEmpty) throw new QueueException("Back called on empty queue.")
    else queue.head

  override def dequeue: Queue[T] =
    if (isEmpty) throw new QueueException("Dequeue called on empty queue.")
    else new Queue[T](queue.init)

  override def top: T =
    if (isEmpty) throw new QueueException("Back called on empty queue.")
    else queue.last

  override def isEmpty: Boolean =
    queue.isEmpty
}