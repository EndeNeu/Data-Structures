package com.ebusiello.fds.tree.generic.queue

trait DoubleEndedQueue[T, S[_]] {

  /**
    * Add an element on the head of the queue
    */
  def prepend(mValue: T): S[T]


  def popLast(): S[T]

  def last(): T
}
