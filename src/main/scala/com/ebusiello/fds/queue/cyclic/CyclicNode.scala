//package com.ebusiello.fds.queue.cyclic
//
//final class CyclicNode[T](val value: T, pointer: AbstractCyclicNode[T]) extends AbstractCyclicNode[T] {
//
//  override def previous: AbstractCyclicNode[T] =
//    pointer
//
//  override def enqueue(mValue: T): AbstractCyclicNode[T] =
//    new CyclicNode[T](value, pointer.enqueue(mValue))
//
//  override def isEmpty: Boolean =
//    false
//
//  override def last: T = pointer match {
//    case e: EmptyCyclicNode[T] => value
//    case _ => pointer.last
//  }
//
//  override def length(accumulator: Int = 0): Int =
//    pointer.length(accumulator + 1)
//}
