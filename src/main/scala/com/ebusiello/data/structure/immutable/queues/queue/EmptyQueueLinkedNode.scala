package com.ebusiello.data.structure.immutable.queues.queue

private[queue] final class EmptyQueueLinkedNode[T]() extends QueueLinkedNode[T](null.asInstanceOf[T], null) {

  override def isEmpty: Boolean =
    true

  override def append(mValue: T): QueueLinkedNode[T] =
    new QueueLinkedNode[T](mValue, new EmptyQueueLinkedNode[T])

  override def size(): Int = 0

  override def stringify: String =
    "E"
}
