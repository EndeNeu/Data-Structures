package com.ebusiello.fds.stacks.stack

/**
 * Represents the end of a linked list.
 */
private[stack] final class EmptyStackNode[T] extends StackNode[T](null.asInstanceOf[T], null) {

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

}