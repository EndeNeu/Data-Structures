package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.{GenericArrayStack, StackException}

import scala.reflect.ClassTag

/**
  *
  *                 | <- push 4
  *          front  |
  *   [v, v, v, 4]
  *                 |
  *                 |-> pop 4
  */
final class StackArray[T: ClassTag] private (buffer: Array[T], pointer: Int, size: Int, private val currentSize: Int) extends GenericArrayStack[T, StackArray]{

  def this(size: Int) = this(new Array[T](size), 0, size, 0)

  override def push(mValue: T): StackArray[T] =
    if(currentSize == size) throw new StackException("Stack is full.")
    else new StackArray[T](updateBuffer(pointer, mValue), pointer + 1, size, currentSize + 1)

  override def top: Option[T] =
    if(isEmpty) None
    else Some(buffer(pointer - 1))

  override def isEmpty: Boolean =
    pointer == 0

  override def pop: StackArray[T] =
    if(isEmpty) throw new StackException("Pop on empty stack.")
    else new StackArray[T](buffer, pointer - 1, size, currentSize - 1)

  private def updateBuffer(index: Int, el: T): Array[T] = {
    buffer.update(index, el)
    buffer
  }
}