package com.ebusiello.data.structure.mutable.queues.queue

import com.ebusiello.data.structure.mutable.queues.QueueArray
import org.scalatest.{ Matchers, WordSpecLike }

class QueueArraySpec extends WordSpecLike with Matchers {

  trait TestContext {
    val queue = new QueueArray[Int]()
  }

  "a mutable QueueArray" should {
    "correctly insert" in new TestContext {
      queue.append(1)
      queue.append(2)
      queue.append(3)
      queue.top.get should be(1)
    }
    "correctly pop" in new TestContext {
      queue.append(1)
      queue.append(2)
      queue.append(3)
      queue.pop
      queue.top.get should be(2)
      queue.pop
      queue.top.get should be(3)
    }

    "be correctly sized" in new TestContext {
      queue.size() should be(0)
      queue.isEmpty should be(true)
      queue.append(1)
      queue.size() should be(1)
      queue.isEmpty should be(false)
      queue.append(2)
      queue.size() should be(2)
      queue.isEmpty should be(false)
      queue.append(3)
      queue.size() should be(3)
      queue.isEmpty should be(false)
      queue.pop
      queue.size() should be(2)
      queue.isEmpty should be(false)
      queue.pop
      queue.size() should be(1)
      queue.isEmpty should be(false)
      queue.pop
      queue.size() should be(0)
      queue.isEmpty should be(true)
    }
  }

}
