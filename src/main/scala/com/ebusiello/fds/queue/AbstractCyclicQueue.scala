package com.ebusiello.fds.queue

abstract class AbstractCyclicQueue[T, S[_]] {

  def push(value: T): S[T]

  def isEmpty: Boolean

  def dequeue: S[T]

  def last: T

}
