//package com.ebusiello.fds.queue.cyclic
//
//import scala.language.higherKinds
//
//abstract class AbstractCyclicQueue[T, S[_]] {
//
//  def enqueue(value: T): S[T]
//
//  def isEmpty: Boolean
//
//  def dequeue: S[T]
//
//  def last: T
//
//  def top: T
//
//}
