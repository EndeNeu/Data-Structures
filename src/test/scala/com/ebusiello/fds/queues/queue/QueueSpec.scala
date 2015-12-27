package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.QueueException
import org.scalatest.{ WordSpecLike, Matchers }

class QueueSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyQueue = new Queue[Int]()
    val nonEmpty: Queue[Int] = Queue[Int](1).enqueue(2).enqueue(5).enqueue(7).enqueue(8)
  }

  "Queue" should {

    "empty" in new TestContext {
      emptyQueue.isEmpty should be(true)
      nonEmpty.isEmpty should be(false)
    }
    "insert" in new TestContext {
      emptyQueue.enqueue(1).isEmpty should be(false)
      emptyQueue.enqueue(1).dequeue.isEmpty should be(true)
      nonEmpty.enqueue(100).top should be(1)

    }

    "top" in new TestContext {
      intercept[QueueException] {
        emptyQueue.top
      }
      nonEmpty.enqueue(10).top should be(1)
      emptyQueue.enqueue(1).top should be(1)
    }

    "dequeue" in new TestContext {
      nonEmpty.enqueue(10).dequeue.top should be(2)
      nonEmpty.dequeue.dequeue.dequeue.dequeue.top should be(8)
      nonEmpty.dequeue.dequeue.dequeue.top should be(7)
      nonEmpty.dequeue.dequeue.top should be(5)
    }

    "size" in new TestContext {
      emptyQueue.size() should be(0)
      nonEmpty.size() should be(5)
    }
  }

}