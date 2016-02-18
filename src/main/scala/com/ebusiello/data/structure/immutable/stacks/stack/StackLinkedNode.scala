package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.GenericStackLinkedNode

/**
 * Nodes are implemented as linked lists, every node holds a value and the pointer to the previous value.
 */
private[stack] class StackLinkedNode[T](val value: T, pointer: StackLinkedNode[T]) extends GenericStackLinkedNode[T, StackLinkedNode] {

  /**
   * Pointer holds a reference to the next element in the list.
   *
   * @return
   */
  override def previous: StackLinkedNode[T] =
    pointer

  override def isEmpty: Boolean =
    false

}
