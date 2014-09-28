package com.ebusiello.fds.stack

import com.ebusiello.fds.queue.queue.QueueException

/**
 * Represents the end of a linked list.
 */
final class EmptyStackNode[T] extends AbstractStackNode[T] {

  /**
   * We return this instead of creating an exception to avoid problems when popping a stack with one element.
   * Pop operations on nodes need to check the pointer's pointer to know the actual status and if there's one node
   * and this would throw exception we would have problem:
   *
   * [1] -> [E]
   *
   * calling pop makes the node [1] look for the [E] next.
   */
  override def previous: EmptyStackNode[T] =
    this

  override def isEmpty: Boolean =
    true

  override def pop: AbstractStackNode[T] =
    throw new QueueException("Pop on empty queue.")

}