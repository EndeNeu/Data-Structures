package com.ebusiello.fds.queue

abstract class AbstractQueue[T, S[_]] {

  def enqueue(mValue: T): S[T]

  def dequeue: S[T]

  def isEmpty: Boolean

  def top: T

  def back: T

}

class QueueException(message: String) extends Exception