package com.ebusiello.data.structure.immutable.queues.queue

import org.scalatest.{ Matchers, WordSpecLike }

class ReversedQueueLinkedSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyQueue = new ReversedQueueLinkedList[Int]()
    val nonEmpty: ReversedQueueLinkedList[Int] = ReversedQueueLinkedList[Int](1).append(2).append(5).append(7).append(8)
  }

  "ReversedQueue" should {

    "empty" in new TestContext {
      emptyQueue.isEmpty should be(true)
      nonEmpty.isEmpty should be(false)
    }
    "insert" in new TestContext {
      emptyQueue.append(1).isEmpty should be(false)
      emptyQueue.append(1).pop.isEmpty should be(true)
      nonEmpty.append(100).top.get should be(1)

    }

    "top" in new TestContext {
      emptyQueue.top should be(None)
      nonEmpty.append(10).top.get should be(1)
      emptyQueue.append(1).top.get should be(1)
    }

    "dequeue" in new TestContext {
      nonEmpty.append(10).pop.top.get should be(2)
      nonEmpty.pop.pop.pop.pop.top.get should be(8)
      nonEmpty.pop.pop.pop.top.get should be(7)
      nonEmpty.pop.pop.top.get should be(5)
    }

    "size" in new TestContext {
      emptyQueue.size() should be(0)
      nonEmpty.size() should be(5)
    }
  }

}