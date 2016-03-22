package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.GenericArrayStack

import scala.reflect.ClassTag

final class StackArray[T: ClassTag] private (buffer: Array[T], pointer: Int, size: Int) extends GenericArrayStack[T, StackArray]{

  def this(size: Int) = this(new Array[T](size), 0, size)

  override def push(mValue: T): StackArray[T] =
    if(buffer.length == size) this
    else new StackArray[T](updateBuffer(pointer, mValue), pointer + 1, size)

  override def top: Option[T] =
    if(isEmpty) None
    else Some(buffer(pointer - 1))

  override def isEmpty: Boolean =
    pointer == 0

  override def pop: StackArray[T] =
    if(isEmpty) this
    else new StackArray[T](buffer, pointer - 1, size)

  private def updateBuffer(index: Int, el: T): Array[T] = {
    buffer.update(index, el)
    buffer
  }
}
