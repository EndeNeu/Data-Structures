package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.{StackException, GenericLinkedStack, GenericStackLinkedNode}

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
final class StackLinked[T] private (head: StackLinkedNode[T]) extends GenericLinkedStack[T, StackLinked] {

  def this() = this(new EmptyStackLinkedNode[T])

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
    case empty: EmptyStackLinkedNode[T] => throw new StackException("Pop on empty stack.")
    case node: StackLinkedNode[T] => new StackLinked[T](node.previous)
  }

}

object StackLinked {
  def empty[T]: StackLinked[T] =
    new StackLinked[T](new EmptyStackLinkedNode[T])

  def apply[T](value: T) =
    new StackLinked[T](new StackLinkedNode[T](value, new EmptyStackLinkedNode[T]))
}

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

/**
  * Represents the end of a linked list.
  */
private[stack] final class EmptyStackLinkedNode[T] extends StackLinkedNode[T](null.asInstanceOf[T], null) {

  /**
    * We return this instead of creating an exception to avoid problems when popping a stack with one element.
    * Pop operations on nodes need to check the pointer's pointer to know the actual status and if there's one node
    * and this would throw exception we would have problem:
    *
    * [1] -> [E]
    *
    * calling pop makes the node [1] look for the [E] next.
    */
  override def previous: EmptyStackLinkedNode[T] =
    this

  override def isEmpty: Boolean =
    true

}