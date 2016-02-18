package com.ebusiello.data.structure.mutable.stacks

import com.ebusiello.data.structure.mutable.queues.QueueArray
import org.scalatest.{ Matchers, WordSpecLike }

class StackArraySpec extends WordSpecLike with Matchers {

  trait TestContext {
    val queue = new StackArray[Int]()
  }

  "a mutable QueueArray" should {
    "correctly insert" in new TestContext {
      queue.push(1)
      queue.push(2)
      queue.push(3)
      queue.top.get should be(3)
    }
    "correctly pop" in new TestContext {
      queue.push(1)
      queue.push(2)
      queue.push(3)
      queue.pop
      queue.top.get should be(2)
      queue.pop
      queue.top.get should be(1)
    }

    "be correctly sized" in new TestContext {
      queue.size() should be(0)
      queue.isEmpty should be(true)
      queue.push(1)
      queue.size() should be(1)
      queue.isEmpty should be(false)
      queue.push(2)
      queue.size() should be(2)
      queue.isEmpty should be(false)
      queue.push(3)
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
