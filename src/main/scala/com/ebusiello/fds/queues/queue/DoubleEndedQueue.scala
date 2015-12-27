package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.GenericQueueNode

import scala.language.higherKinds

trait DoubleEndedQueue[T, S[T] <: GenericQueueNode[T, S]] {

  val root: S[T]

}
