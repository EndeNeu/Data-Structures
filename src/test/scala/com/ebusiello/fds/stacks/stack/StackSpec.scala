package com.ebusiello.fds.stacks.stack

import org.scalatest.{ Matchers, WordSpecLike }

class StackSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyStack = Stack.empty[Int]
    val nonEmptyStack = new Stack[Int]().push(1).push(7).push(3).push(5)
  }

  "A empty Stack" should {
    "be empty" in new TestContext {
      emptyStack.isEmpty should be(true)
      emptyStack.push(3).isEmpty should be(false)
    }
    "insert" in new TestContext {
      emptyStack.push(1).top should be(1)
      nonEmptyStack.push(10).top should be(10)
      nonEmptyStack.top should be(5)
    }
    "pop" in new TestContext {
      nonEmptyStack.pop.top should be(3)
      nonEmptyStack.pop.pop.top should be(7)
    }
  }

  "A non empty stack" should {
    "not be empty" in new TestContext {
      nonEmptyStack.isEmpty should be(false)
      nonEmptyStack.pop.pop.pop.pop.isEmpty should be(true)
    }
    "correctly insert" in new TestContext {
      nonEmptyStack.push(100).top should be(100)
      nonEmptyStack.push(100).pop.top should be(5)
    }
  }

}
