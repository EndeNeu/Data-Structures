//package com.ebusiello.fds.com.ebusiello.fds.queue.com.ebusiello.fds.queue
//
//final class EmptyQueueNode[T] extends AbstractQueueNode[T] {
//
//  override def dequeue: AbstractQueueNode[T] =
//    throw new QueueException("Dequeue on empty queue")
//
//  override def isEmpty: Boolean =
//    true
//
//  override def previous: AbstractQueueNode[T] =
//    this
//
//  override def enqueue(mValue: T): AbstractQueueNode[T] =
//    new QueueNode[T](mValue, new EmptyQueueNode[T])
//
//}
