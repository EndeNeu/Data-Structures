package com.ebusiello.fds.stack

abstract class AbstractStackNode[T] {

  def next: AbstractStackNode[T]

  def top: T

  def isEmpty: Boolean

  def pop: AbstractStackNode[T]

}
