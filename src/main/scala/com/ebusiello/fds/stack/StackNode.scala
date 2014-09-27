package com.ebusiello.fds.stack

/**
 * Nodes are implemented as linked lists, every node holds a value and the pointer to the next value.
 */
final class StackNode[T](value: T, pointer: AbstractStackNode[T]) extends AbstractStackNode[T] {

  /**
   * Pointer holds a reference to the next element in the list.
   * @return
   */
  override def next: AbstractStackNode[T] =
    pointer

  /**
   * If the next node is empty we reach the end of the list and so we should return this value,
   * otherwise keep propagating the top.
   */
  override def top: T = pointer match {
    case empty: EmptyStackNode[T] => value
    case _ => pointer.top
  }

  override def isEmpty: Boolean =
    false

  /**
   * We pop a node checking the next two node relative to this node.
   *
   * 1) if the nex it's empty means that the stack has only one node and popping should return an empty stack:
   *
   * [1] -> [E] ---> pop ---> [E]
   *
   * 2) if the next is not empty, but the next.next is, remove the immediate next node:
   *
   * [1] -> [2] -> [E]  ---> pop ---> [1] -> [E]
   *
   * 3) if the next.next is not empty, delegate the pop to the next node.
   *
   * [1] -> [2] -> [3] -> [E] ---> pop ---> [1] -> [2] -> [E]
   *
   *
   */
  override def pop: AbstractStackNode[T] = pointer match {
    case empty: EmptyStackNode[T] => new EmptyStackNode[T]
    case stackNode => stackNode.next match {
      case empty: EmptyStackNode[T] => new StackNode[T](value, new EmptyStackNode[T])
      case next => new StackNode[T](value, next.pop)
    }

  }

}
