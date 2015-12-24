package com.ebusiello.fds.queues.queue

import org.scalatest.{ WordSpecLike, Matchers }

class QueueSpec extends WordSpecLike with Matchers {

  val emptyQueue = new Queue[Int]()
  val nonEmpty: Queue[Int] = Queue[Int](1).enqueue(2).enqueue(5).enqueue(7).enqueue(8)

  "Queue" should {

    "empty" in {
      emptyQueue.isEmpty should be(true)
      nonEmpty.isEmpty should be(false)
    }
    "insert" in {
      emptyQueue.enqueue(1).isEmpty should be(false)
      emptyQueue.enqueue(1).top should be(1)
      emptyQueue.enqueue(1).dequeue.isEmpty should be(true)

      nonEmpty.enqueue(10).top should be(1)
      nonEmpty.enqueue(10).dequeue.top should be(2)

      nonEmpty.dequeue.dequeue.dequeue.dequeue.top should be(8)
      nonEmpty.dequeue.dequeue.dequeue.top should be(7)
      nonEmpty.dequeue.dequeue.top should be(5)

    }
  }

}