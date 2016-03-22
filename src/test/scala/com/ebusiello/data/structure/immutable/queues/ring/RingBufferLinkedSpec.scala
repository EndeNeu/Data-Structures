package com.ebusiello.data.structure.immutable.queues.ring

import org.scalatest.{ Matchers, WordSpecLike }

class RingBufferLinkedSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val empty = new RingBufferLinked[Int](3)
    val nonEmpty = new RingBufferLinked[Int](3).append(2).append(1)
  }

  "CyclicQueue" should {

    "empty" in new TestContext {
      empty.isEmpty should be(true)
      empty.append(1).isEmpty should be(false)
      nonEmpty.isEmpty should be(false)
      nonEmpty.pop.pop.isEmpty should be(true)
    }
    "enqueue" in new TestContext {
      empty.append(1).append(2).append(3).top.get should be(1)
      empty.append(1).append(2).pop.top.get should be(2)
      empty.append(1).append(2).append(3).pop.pop.top.get should be(3)
      empty.append(1).append(2).append(3).append(4).top.get should be(2)
      empty.append(1).append(2).append(3).append(4).pop.pop.top.get should be(4)
      empty.append(1).append(2).append(3).append(4).append(5).top.get should be(3)
    }
    "last" in new TestContext {
      nonEmpty.last.get should be(1)
    }

    "throw when empty on top" in new TestContext {
      empty.top should be(None)
    }
  }
}
