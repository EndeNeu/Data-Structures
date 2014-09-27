package com.ebusiello.fds.queue.cyclic

import com.ebusiello.fds.queue.{QueueException, AbstractCyclicQueue}

/**
 * Cyclicity is ensured by having the oldest element always at the head of a list,
 * push appends an element to a list and if the size is reached and the overwrite flag
 * is set to true the head is dropped, the second element because the head
 * and the new element is appended to the list.
 *
 *
 * size 3
 * push 1 -> |1|
 * push 2 -> |1|2|
 * push 3 -> |1|2|3| full list
 * push 4 -> |2|3|4|
 *
 */
final class CyclicQueue[T](size: Long, overwrite: Boolean = true, private[this] val queue: List[T] = List()) extends AbstractCyclicQueue[T, CyclicQueue] {

  override def push(value: T): CyclicQueue[T] =
    queue match {
      case head :: tail =>
        // if the size is reached and overwrite is set, drop the ehad and append the newest.
        if (queue.size == size && overwrite) new CyclicQueue[T](size, overwrite, tail :+ value)
        else if(queue.size == size && !overwrite) throw new QueueException("Cyclic queue size exhausted.")
        else new CyclicQueue[T](size, overwrite, queue :+ value)
      // if the list is empty create a new queue with one element.
      case _ => new CyclicQueue[T](size, overwrite, List(value))
    }

  /**
   * Dequeue always takes the first inserted element which in our case is the head
   * of the list.
   */
  override def dequeue: CyclicQueue[T] =
    queue match {
      case head :: tail =>
        new CyclicQueue[T](size, overwrite, tail)
      case _ =>
        throw new QueueException("dequeue called on empty queue.")
    }

  override def last: T =
    if (isEmpty) throw new QueueException("last called on empty queue.")
    else queue.head

  override def isEmpty: Boolean =
    queue.isEmpty
}