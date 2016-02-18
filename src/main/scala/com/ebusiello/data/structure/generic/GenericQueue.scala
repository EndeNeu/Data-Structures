package com.ebusiello.data.structure.generic

import scala.language.higherKinds
import scala.reflect.ClassTag

private[structure] trait GenericQueue[T, S[_]] {

  /**
   * Adds an item onto the end of the queue.
   */
  def append(mValue: T): S[T]

  /**
   * Removes the item from the front of the queue.
   */
  def pop: S[T]

  /**
   * Returns the item at the front of the queue.
   */
  def top: Option[T]

  def isEmpty: Boolean

  def size(): Int

  def stringify()(implicit ev: ClassTag[S[_]]): String
}
