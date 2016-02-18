package com.ebusiello.data.structure.mutable.stacks

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

class StackArray[T: ClassTag]() extends GenericMutableStack[T] {

  private val stack = ArrayBuffer.empty[T]
  private var stackSize: Int = 0

  /**
   * Removes the item from the front of the queue.
   */
  override def pop: Option[T] =
    if (stackSize == 0) None
    else {
      stackSize -= 1
      Some(stack.remove(stackSize))
    }

  override def size(): Int =
    stackSize

  override def push(mValue: T): Unit = {
    stackSize += 1
    stack += mValue
  }

  /**
   * Returns the item at the front of the queue.
   */
  override def top: Option[T] =
    if (stackSize == 0) None
    else Some(stack(stackSize - 1))

  override def stringify(): String =
    if (stackSize == 0) "E | T"
    else stack.mkString("E |", "|", "| T")

  override def isEmpty: Boolean =
    stackSize == 0
}
