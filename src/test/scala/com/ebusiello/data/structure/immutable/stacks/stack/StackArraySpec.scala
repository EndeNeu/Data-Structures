package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.StackException
import org.scalatest.{Matchers, WordSpecLike}

class StackArraySpec extends WordSpecLike with Matchers {

  trait TestContext {
    var stack = new StackArray[Int](4)
  }

  "A empty StackLinked" should {
    "be empty" in new TestContext {
      stack.isEmpty should be(true)
      stack.nonEmpty should be(false)
      stack.push(3).isEmpty should be(false)
      stack.push(3).nonEmpty should be(true)
      stack.top should be(None)
    }
    "insert" in new TestContext {
      stack.push(1).top.get should be(1)
    }
    "throw when popping" in new TestContext {
      intercept[StackException] {
        stack.pop
      }
    }

    "throw when topping" in new TestContext {
      stack.top should be(None)
    }
  }

  "A non empty stack" should {
    "not be empty" in new TestContext {
      stack.isEmpty should be(true)
      stack = stack.push(1)
      stack = stack.push(2)
      stack = stack.push(3)
      stack = stack.push(4)
      stack.pop.pop.pop.pop.isEmpty should be(true)
    }

    "correctly insert" in new TestContext {
      stack.push(100).top.get should be(100)
      stack.push(1).push(100).pop.top.get should be(1)
    }

    "not overflow the array" in new TestContext {
      intercept[StackException] {
        stack.push(1).push(2).push(2).push(2).push(2)
      }
    }

  }

}
