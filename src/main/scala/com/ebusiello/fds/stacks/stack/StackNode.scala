package com.ebusiello.fds.stacks.stack

import com.ebusiello.fds.stacks.GenericStackNode

/**
 * Nodes are implemented as linked lists, every node holds a value and the pointer to the previous value.
 */
class StackNode[T](val value: T, pointer: StackNode[T]) extends GenericStackNode[T, StackNode] {

  /**
   * Pointer holds a reference to the next element in the list.
   * @return
   */
  override def previous: StackNode[T] =
    pointer

  override def isEmpty: Boolean =
    false

}
