package com.ebusiello.fds.stack

final class Stack[T](stack: List[T] = List()) extends AbstractStack[T, Stack] {

  private[this] val mStack: List[T] = stack

  override def push(mValue: T): Stack[T] =
    new Stack[T](mStack :+ mValue)

  override def pushCollection(collection: Traversable[T]): Stack[T] =
    new Stack[T](mStack ++ collection)

  override def top: T =
    if (nonEmpty) mStack.last
    else throw new StackException("top on empty stack!")

  override def isEmpty: Boolean =
    mStack.isEmpty

  def nonEmpty: Boolean =
    !isEmpty

  override def pop: Stack[T] =
    if (nonEmpty) new Stack[T](mStack.init)
    else throw new StackException("pop on empty stack!")

}


object Stack {
  def empty[T]: Stack[T] =
    new Stack[T]()
}

class StackException(message: String) extends Exception