package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.QueueException
import org.scalatest.{ WordSpecLike, Matchers }

class QueueSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyQueue = new Queue[Int]()
    val nonEmpty: Queue[Int] = Queue[Int](1).append(2).append(5).append(7).append(8)
  }

  "Queue" should {

    "empty" in new TestContext {
      emptyQueue.isEmpty should be(true)
      nonEmpty.isEmpty should be(false)
    }
    "insert" in new TestContext {
      emptyQueue.append(1).isEmpty should be(false)
      emptyQueue.append(1).pop.isEmpty should be(true)
      nonEmpty.append(100).top should be(1)

    }

    "top" in new TestContext {
      intercept[QueueException] {
        emptyQueue.top
      }
      nonEmpty.append(10).top should be(1)
      emptyQueue.append(1).top should be(1)
    }

    "dequeue" in new TestContext {
      nonEmpty.append(10).pop.top should be(2)
      nonEmpty.pop.pop.pop.pop.top should be(8)
      nonEmpty.pop.pop.pop.top should be(7)
      nonEmpty.pop.pop.top should be(5)
    }

    "size" in new TestContext {
      emptyQueue.size() should be(0)
      nonEmpty.size() should be(5)
    }
  }

}