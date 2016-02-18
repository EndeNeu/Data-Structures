package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.GenericLinkedStack

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
final class StackLinked[T](head: StackLinkedNode[T] = new EmptyStackLinkedNode[T]) extends GenericLinkedStack[T, StackLinked] {

  /**
   * Pushing a value changes the head of the stack and the current head becomes
   * the previous of the new node.
   */
  override def push(mValue: T): StackLinked[T] =
    new StackLinked[T](new StackLinkedNode[T](mValue, head))

  override def top: Option[T] = head match {
    case e: EmptyStackLinkedNode[T] => None
    case n: StackLinkedNode[T] => Some(n.value)
  }

  override def isEmpty: Boolean =
    head.isEmpty

  def nonEmpty: Boolean =
    !isEmpty

  override def pop: StackLinked[T] = head match {
    case empty: EmptyStackLinkedNode[T] => this
    case node: StackLinkedNode[T] => new StackLinked[T](node.previous)
  }

}

object StackLinked {
  def empty[T]: StackLinked[T] =
    new StackLinked[T](new EmptyStackLinkedNode[T])

  def apply[T](value: T) =
    new StackLinked[T](new StackLinkedNode[T](value, new EmptyStackLinkedNode[T]))
}