package com.ebusiello.fds.queues.cyclic

/**
 * Cyclicity is virtual, the nodes are a linked list where the last node points to an empty one
 * like in any normal com.ebusiello.fds.queue, cyclicity is enforced by the com.ebusiello.fds.queue which keeps track of the list length,
 * if the maximum length is reached drop the head and ppint to the second element.
 */
/*final class CyclicQueue[T](size: Int, head: AbstractCyclicNode[T] = new EmptyCyclicNode[T]) extends AbstractCyclicQueue[T, CyclicQueue] {

  //TODO insert is O(N) because we need the length of the list.
  override def enqueue(value: T): CyclicQueue[T] =
    if (head.length() == size) new CyclicQueue[T](size, head.previous.enqueue(value))
    else new CyclicQueue[T](size, head.enqueue(value))

  /**
   * Dequeue always takes the first inserted element which in our case is the head.
   */
  override def dequeue: CyclicQueue[T] =
    new CyclicQueue[T](size, head.previous)

  override def last: T =
    head.last

  override def isEmpty: Boolean =
    head.isEmpty

  override def top: T = head match {
    case e: EmptyCyclicNode[T] => throw new QueueException("top on empty queue.")
    case n: CyclicNode[T] => n.value
  }

}*/ 