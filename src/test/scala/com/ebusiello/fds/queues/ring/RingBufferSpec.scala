package com.ebusiello.fds.queues.ring

import com.ebusiello.fds.queues.QueueException
import org.scalatest.{ Matchers, WordSpecLike }

class RingBufferSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val empty = new RingBuffer[Int](3)
    val nonEmpty = new RingBuffer[Int](3).append(2).append(1)
  }

  "CyclicQueue" should {

    "empty" in new TestContext {
      empty.isEmpty should be(true)
      empty.append(1).isEmpty should be(false)
      nonEmpty.isEmpty should be(false)
      nonEmpty.pop.pop.isEmpty should be(true)
    }
    "enqueue" in new TestContext {
      empty.append(1).append(2).append(3).top should be(1)
      empty.append(1).append(2).pop.top should be(2)
      empty.append(1).append(2).append(3).pop.pop.top should be(3)
      empty.append(1).append(2).append(3).append(4).top should be(2)
      empty.append(1).append(2).append(3).append(4).pop.pop.top should be(4)
      empty.append(1).append(2).append(3).append(4).append(5).top should be(3)
    }
    "last" in new TestContext {
      nonEmpty.last should be(1)
    }

    "throw when empty on top" in new TestContext {
      intercept[QueueException] {
        empty.top
      }
    }

  }

}
