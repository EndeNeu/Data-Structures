//package com.ebusiello.fds.com.ebusiello.fds.queue.com.ebusiello.fds.queue
//
//import scala.language.higherKinds
//
//abstract class AbstractQueue[T, S[_]] {
//
//  def enqueue(mValue: T): S[T]
//
//  def dequeue: S[T]
//
//  def isEmpty: Boolean
//
//  def top: T
//
//}