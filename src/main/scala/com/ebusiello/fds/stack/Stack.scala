package com.ebusiello.fds.stack

/**
 * Stacks hold a reference to the first element in the linked list, operations
 * are propagated using the head.
 */
final class Stack[T](head: AbstractStackNode[T]) extends AbstractStack[T, Stack] {

  /**
   * Pushing a value changes the head of the stack and the current head becomes
   * the next of the new node.
   */
  override def push(mValue: T): Stack[T] =
    new Stack[T](new StackNode[T](mValue, head))

  override def top: T =
    if (nonEmpty) head.top
    else throw new StackException("top on empty stack!")

  override def isEmpty: Boolean =
    head.isEmpty

  def nonEmpty: Boolean =
    !isEmpty

  override def pop: Stack[T] =
    if (nonEmpty) new Stack[T](head.pop)
    else throw new StackException("pop on empty stack!")

}


object Stack {
  def empty[T]: Stack[T] =
    new Stack[T](new EmptyStackNode[T])

  def apply[T](value: T) =
    new Stack[T](new StackNode[T](value, new EmptyStackNode[T]))
}

class StackException(message: String) extends Exception