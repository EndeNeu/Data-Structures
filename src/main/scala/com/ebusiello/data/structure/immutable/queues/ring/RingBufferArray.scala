package com.ebusiello.data.structure.immutable.queues.ring

import com.ebusiello.data.structure.immutable.queues.GenericArrayQueue

import scala.reflect.ClassTag

/**
 * [1, 2, 3, E]
 *           ^
 *           |
 *          pointer
 *
 *    [1, 2, 3, 4]
 *     ^
 *     |
 *    pointer
 */
final class RingBufferArray[T: ClassTag] private (val buffer: Array[T], val pointer: Int, val size: Int, val currentSize: Int) extends GenericArrayQueue[T, RingBufferArray] {

  // TODO pointers hell to avoid array shifting, how can this be refactored in a more readable way?

  def this(size: Int) = this(new Array[T](size), 0, size, 0)

  /**
   * there are 3 cases when adding a value.
   *
   * (1) the ring is full , if the pointer is bigger that the ring size start from the 0 index of the array
   *
   * [1, 2, 3, 4] size = 4 pointer = 4
   *           ^
   *         pointer
   *
   * update the value at the pointer without changing the current size
   *
   *  [5, 1, 3, 4]
   *      ^
   *   pointer
   *
   * (2) the ring is full and case (1) just happened
   *
   *  [5, 2, 3, 4]
   *      ^
   *    pointer
   *
   * update the first element of the array and make the pointer go to the second element without changing the size
   *
   * [5, 6, 3, 4]
   *        ^
   *      pointer
   *
   *
   * (3) normal case, append at the end of the ring
   *
   * [1, 2, 3, E]
   *           ^
   *          pointer
   *
   * add the element at the end of the array and increase the size
   *
   */
  override def append(mValue: T): RingBufferArray[T] = {
    // we reached the end of the ring
    if (currentSize == size) {
      // else if the pointer is bigger than the size means we have to start from the 0 index of the array
      if (pointer >= currentSize) new RingBufferArray[T](updateBuffer(0, mValue), 1, size, currentSize)
      else new RingBufferArray[T](updateBuffer(pointer, mValue), pointer + 1, size, currentSize)
    } // otherwise it's just a normal insert
    else new RingBufferArray[T](updateBuffer(pointer, mValue), pointer + 1, size, currentSize + 1)
  }

  /**
   * (1) If the ring is empty there's nothing to pop
   *
   * (2) if the pointer is pointing to the second element and the ring is not empty we need to go backwards
   * so start from the index at size
   *
   *
   *  [1, E, 3, 4] size = 3
   *   ^
   * pointer
   *
   *  [E, E, 3, 4]    size = 2
   *            ^
   *          pointer
   *
   * (3) if the size is one
   *
   *  [1, E, E, E] size = 1
   *      ^
   *    pointer
   *
   *  reset the ring
   *
   *  [E, E, E, E] size = 0
   *   ^
   * pointer
   *
   * (4) else it's just a normal pop
   *
   *  [1, 2, 3, E] size = 3
   *         ^
   *       pointer
   *
   *  [1, 2, E, E]
   *      ^
   *    pointer
   */
  override def pop: RingBufferArray[T] = {
    if (isEmpty) this
    // if the pointer is at the first element because we are reiterating the ring,
    // point to the last element of the array (walking the ring backwards) which is at the size index
    else if (pointer == 1 && currentSize > 1) new RingBufferArray[T](buffer, size, size, currentSize - 1)
    // else if there's one element in the ring, reset the ring and the pointer
    else if (currentSize == 1) new RingBufferArray[T](buffer, 0, size, 0)
    // otherwise it's just a normal pop.
    else new RingBufferArray[T](buffer, pointer - 1, size, currentSize - 1)
  }

 /**
   * if there's a pointer use it.
   */
  override def top: Option[T] = {
    if (isEmpty) None
    else if (pointer > 0) Some(buffer(pointer - 1))
    else None
  }

  override def length(): Int =
    currentSize

  override def stringify(): String =
    if (isEmpty) "E | T"
    else _stringify() + "| T"

  /**
    * Helper method that recursively pops the ring and aggregate the strings.
    */
  private def _stringify(): String = {
    if (isEmpty) "E "
    else pop._stringify() + s"|${top.get}"
  }

  override def isEmpty: Boolean =
    currentSize == 0

  def isFull: Boolean =
    size == currentSize

  /**
    * Helper kestrel combinator to update the buffer.
    */
  private def updateBuffer(index: Int, elem: T): Array[T] = {
    buffer.update(index, elem)
    buffer
  }
}