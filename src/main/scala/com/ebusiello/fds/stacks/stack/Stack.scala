package com.ebusiello.fds.stacks.stack

import com.ebusiello.fds.stacks.{ StackException, GenericStack }

/**
 * LIFO
 *
 * Stacks hold a reference to the first element in the linked list (head).
 *
 *                 | <- push 4
 *          front  |
 *   [v, v, v, 4]
 *                 |
 *                 |-> pop 4
 *
 *
 */
final class Stack[T](head: StackNode[T] = new EmptyStackNode[T]) extends GenericStack[T, Stack] {

  /**
   * Pushing a value changes the head of the stack and the current head becomes
   * the previous of the new node.
   */
  override def push(mValue: T): Stack[T] =
    new Stack[T](new StackNode[T](mValue, head))

  override def top: T = head match {
    case e: EmptyStackNode[T] => throw new StackException("Top on empty stack.")
    case n: StackNode[T] => n.value
  }

  override def isEmpty: Boolean =
    head.isEmpty

  def nonEmpty: Boolean =
    !isEmpty

  override def pop: Stack[T] = head match {
    case empty: EmptyStackNode[T] => throw new StackException("Pop on empty stack.")
    case node: StackNode[T] => new Stack[T](node.previous)
  }

}

object Stack {
  def empty[T]: Stack[T] =
    new Stack[T](new EmptyStackNode[T])

  def apply[T](value: T) =
    new Stack[T](new StackNode[T](value, new EmptyStackNode[T]))
}

