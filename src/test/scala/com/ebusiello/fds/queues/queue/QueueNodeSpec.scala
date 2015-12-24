package com.ebusiello.fds.queues.queue

import com.ebusiello.fds.queues.QueueException
import org.scalatest.{ WordSpecLike, Matchers }

class QueueNodeSpec extends Matchers with WordSpecLike {

  trait TestContext {
    val emptyNode = new EmptyQueueNode[Int]()
    val node = new QueueNode[Int](0, new EmptyQueueNode[Int])
  }

  "an empty node" should {
    "correctly insert" in new TestContext {
      emptyNode.enqueue(1).value should be(1)
      emptyNode.enqueue(1).pointer shouldBe a[EmptyQueueNode[_]]
    }

    "correctly get the size" in new TestContext {
      emptyNode.size() should be(0)
    }

    "correctly report if it's empty" in new TestContext {
      emptyNode.isEmpty should be(true)
      emptyNode.enqueue(1).isEmpty should be(false)
    }

    "throw when getting the previous" in new TestContext {
      intercept[QueueException] {
        emptyNode.previous
      }
    }
  }

  "a non empty node" should {
    "correctly insert" in new TestContext {
      node.enqueue(1).value should be(0)
      node.enqueue(1).pointer.value should be(1)
    }

    "correctly get the size" in new TestContext {
      node.size() should be(1)
      node.enqueue(2).size() should be(2)
    }

    "correctly report if it's empty" in new TestContext {
      node.isEmpty should be(false)
    }

    "throw when getting the previous" in new TestContext {
      node.previous shouldBe a[EmptyQueueNode[_]]
      node.enqueue(0).previous.value should be(0)
    }
  }

}
