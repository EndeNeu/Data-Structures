package com.ebusiello.data.structure.immutable.stacks.stack

import com.ebusiello.data.structure.immutable.stacks.StackException
import org.scalatest.{ Matchers, WordSpecLike }

class StackSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyStack = Stack.empty[Int]
    val nonEmptyStack = Stack[Int](1).push(7).push(3).push(5)
  }

  "A empty Stack" should {
    "be empty" in new TestContext {
      emptyStack.isEmpty should be(true)
      emptyStack.nonEmpty should be(false)
      emptyStack.push(3).isEmpty should be(false)
    }
    "insert" in new TestContext {
      emptyStack.push(1).top should be(1)
    }
    "throw when popping" in new TestContext {
      intercept[StackException] {
        emptyStack.pop
      }
    }

    "throw when topping" in new TestContext {
      intercept[StackException] {
        emptyStack.top
      }
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

    "correctly pop" in new TestContext {
      nonEmptyStack.pop.top should be(3)
      nonEmptyStack.pop.pop.top should be(7)
    }

    "correctly top" in new TestContext {
      nonEmptyStack.top should be(5)
      nonEmptyStack.pop.top should be(3)
    }
  }

}
