package com.ebusiello.fds.queue.queue

abstract class AbstractQueueNode[T] {

  def previous: AbstractQueueNode[T]

  def dequeue: AbstractQueueNode[T]

  def isEmpty: Boolean

  def enqueue(mValue: T): AbstractQueueNode[T]

}