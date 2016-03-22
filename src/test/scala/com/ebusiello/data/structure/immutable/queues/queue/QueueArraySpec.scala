package com.ebusiello.data.structure.immutable.queues.queue

import org.scalatest.{ Matchers, WordSpecLike }

class QueueArraySpec extends WordSpecLike with Matchers {

  trait TestContext {
    val queue = new QueueArray[Int]()
  }

  "an immutable queue array" should {
    "correctly append" in new TestContext {
      queue.isEmpty should be(true)
      //queue.top should be(None)
      //queue.pop.isEmpty should be(true)

      val newQueue = queue.append(1).append(4).append(9)

      newQueue.length() should be(3)
      newQueue.top.get should be(1)
      newQueue.pop.top.get should be(4)
    }

    "correctly stringify" in new TestContext {
      val newQueue = queue.append(1).append(4).append(9)
      newQueue.stringify() should be("E |9|4|1| T")

    }
  }

}
