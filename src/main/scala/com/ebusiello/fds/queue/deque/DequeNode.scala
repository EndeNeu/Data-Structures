package com.ebusiello.fds.queue.deque

final class DequeNode[T](val value: T, pointer: AbstractDequeNode[T]) extends AbstractDequeNode[T] {

  override def isEmpty: Boolean =
    false

  override def last: T = previous match {
    case e: EmptyDequeNode[T] => value
    case _ => previous.last
  }

  /**
   * Append is propagated until we find the empty node.
   */
  override def append(mValue: T): AbstractDequeNode[T] =
    new DequeNode[T](value, previous.append(mValue))

  override def previous: AbstractDequeNode[T] =
    pointer

  /**
   * if the pointer points to an empty node it means this is the last node, pop it and return an empty node;
   * if the next node pointer has an empty node, this is the second to last node, return this with a pointer to an empty node
   * otherwise propagate the operation to the next node.
   */
  override def popLast: AbstractDequeNode[T] = pointer match {
    case e: EmptyDequeNode[T] => e
    case n: DequeNode[T] => n.previous match {
      case e: EmptyDequeNode[T] => new DequeNode[T](value, new EmptyDequeNode[T])
      case n: DequeNode[T] => new DequeNode[T](value, n.popLast)
    }
  }
}
