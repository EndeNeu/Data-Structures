package com.ebusiello.fds.queues.ring

import org.scalatest.{ Matchers, WordSpecLike }

class RingBufferSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val empty = new RingBuffer[Int](3)
    val nonEmpty = new RingBuffer[Int](3).enqueue(2).enqueue(1)
  }

  "CyclicQueue" should {

    "empty" in new TestContext {
      empty.isEmpty should be(true)
      empty.enqueue(1).isEmpty should be(false)
      nonEmpty.isEmpty should be(false)
      nonEmpty.dequeue.dequeue.isEmpty should be(true)
    }
    "enqueue" in new TestContext {
      empty.enqueue(1).enqueue(2).enqueue(3).top should be(1)
      empty.enqueue(1).enqueue(2).dequeue.top should be(2)
      empty.enqueue(1).enqueue(2).enqueue(3).dequeue.dequeue.top should be(3)
      empty.enqueue(1).enqueue(2).enqueue(3).enqueue(4).top should be(2)
      empty.enqueue(1).enqueue(2).enqueue(3).enqueue(4).dequeue.dequeue.top should be(4)
      empty.enqueue(1).enqueue(2).enqueue(3).enqueue(4).enqueue(5).top should be(3)
    }
    "last" in new TestContext {
      nonEmpty.last should be(1)
    }

  }

}
