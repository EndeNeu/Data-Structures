package com.ebusiello.data.structure.immutable.queues.ring

import org.scalatest.{ Matchers, WordSpecLike }

class RingBufferNodeSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val empty = new EmptyRingBufferNode[Int]
    val nonEmpty = new RingBufferNode[Int](1, empty)
  }

  "a non empty node" should {
    "correctly append" in new TestContext {
      val newNode = nonEmpty.append(2)
      newNode.value should be(1)
      newNode.next.value should be(2)
    }

    "be non empty" in new TestContext {
      nonEmpty.isEmpty should be(false)
    }

    "should return the last" in new TestContext {
      nonEmpty.last.get should be(1)
      nonEmpty.append(2).last.get should be(2)
    }

    "correctly return the length" in new TestContext {
      nonEmpty.length() should be(1)
      nonEmpty.append(2).length() should be(2)
    }

    "correctly stringify" in new TestContext {
      nonEmpty.stringify should be("1, E")
    }
  }

  "a empty node" should {
    "correctly append" in new TestContext {
      val newNode = empty.append(2)
      newNode.value should be(2)
      newNode.next.isEmpty should be(true)
    }

    "be empty" in new TestContext {
      empty.isEmpty should be(true)
    }

    "should return en exception on last" in new TestContext {
      empty.last should be(None)
    }

    "correctly return the length" in new TestContext {
      empty.length() should be(0)
      empty.append(2).length() should be(1)
    }

    "correctly stringify" in new TestContext {
      empty.stringify should be("E")
    }
  }

}
