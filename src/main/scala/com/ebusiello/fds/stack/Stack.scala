//package com.ebusiello.fds.stack
//
///**
// * LIFO
// *
// * Stacks hold a reference to the first element in the linked list (head).
// *
// *                 | <- push 4
// *          front  |
// *   [v, v, v, 4]
// *                 |
// *                 |-> pop 4
// *
// *
// */
//final class Stack[T](head: AbstractStackNode[T] = new EmptyStackNode[T]) extends AbstractStack[T, Stack] {
//
//  /**
//   * Pushing a value changes the head of the stack and the current head becomes
//   * the previous of the new node.
//   */
//  override def push(mValue: T): Stack[T] =
//    new Stack[T](new StackNode[T](mValue, head))
//
//  override def top: T = head match {
//    case e: EmptyStackNode[T] => throw new StackException("Top on empty stack")
//    case n: StackNode[T] => n.value
//  }
//
//  override def isEmpty: Boolean =
//    head.isEmpty
//
//  def nonEmpty: Boolean =
//    !isEmpty
//
//  override def pop: Stack[T] =
//    new Stack[T](head.previous)
//
//}
//
//object Stack {
//  def empty[T]: Stack[T] =
//    new Stack[T](new EmptyStackNode[T])
//
//  def apply[T](value: T) =
//    new Stack[T](new StackNode[T](value, new EmptyStackNode[T]))
//}
//
//class StackException(message: String) extends Exception