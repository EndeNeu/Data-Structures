package com.ebusiello.data.structure.immutable.queues.queue

import org.scalatest.{ Matchers, WordSpecLike }

class QueueNodeSpec extends Matchers with WordSpecLike {

  trait TestContext {
    val emptyNode = new EmptyQueueLinkedNode[Int]()
    val node = new QueueLinkedNode[Int](0, new EmptyQueueLinkedNode[Int])
  }

  "an empty node" should {
    "correctly insert" in new TestContext {
      emptyNode.append(1).value should be(1)
      emptyNode.append(1).next shouldBe a[EmptyQueueLinkedNode[_]]
    }

    "correctly get the size" in new TestContext {
      emptyNode.size() should be(0)
    }

    "correctly report if it's empty" in new TestContext {
      emptyNode.isEmpty should be(true)
      emptyNode.append(1).isEmpty should be(false)
    }

  }

  "a non empty node" should {
    "correctly insert" in new TestContext {
      node.append(1).value should be(0)
      node.append(1).next.value should be(1)
    }

    "correctly get the size" in new TestContext {
      node.size() should be(1)
      node.append(2).size() should be(2)
    }

    "correctly report if it's empty" in new TestContext {
      node.isEmpty should be(false)
    }

    "throw when getting the previous" in new TestContext {
      node.next shouldBe a[EmptyQueueLinkedNode[_]]
      node.append(0).next.value should be(0)
    }

    "correctly stringify" in new TestContext {
      emptyNode.stringify should be("E")
      node.stringify should be("0, E")
    }
  }

}
