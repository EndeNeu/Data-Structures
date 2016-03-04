package com.ebusiello.data.structure.immutable.queues.ring

private[ring] final class EmptyRingBufferNode[T] extends RingBufferNode[T](null.asInstanceOf[T], null) {

  override def append(mValue: T): RingBufferNode[T] =
    new RingBufferNode[T](mValue, new EmptyRingBufferNode[T])

  override def isEmpty: Boolean =
    true

  override def last: Option[T] =
    None

  override def length(): Int =
    0

  override def stringify: String =
    "E"

  override def top: Option[T] =
    None

  override def pop: RingBufferNode[T] =
    this
}

