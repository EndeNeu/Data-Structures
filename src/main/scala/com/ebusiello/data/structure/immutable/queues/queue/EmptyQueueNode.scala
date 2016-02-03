package com.ebusiello.data.structure.immutable.queues.queue

final class EmptyQueueNode[T]() extends QueueNode[T](null.asInstanceOf[T], null) {

  override def isEmpty: Boolean =
    true

  override def append(mValue: T): QueueNode[T] =
    new QueueNode[T](mValue, new EmptyQueueNode[T])

  override def size(): Int = 0

  override def stringify: String =
    "E"
}
