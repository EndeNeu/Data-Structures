package com.ebusiello.data.structure.immutable.stacks.stack

import org.scalatest.{ Matchers, WordSpecLike }

class StackSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyStack = StackLinked.empty[Int]
    val nonEmptyStack = StackLinked[Int](1).push(7).push(3).push(5)
  }

  "A empty StackLinked" should {
    "be empty" in new TestContext {
      emptyStack.isEmpty should be(true)
      emptyStack.nonEmpty should be(false)
      emptyStack.push(3).isEmpty should be(false)
    }
    "insert" in new TestContext {
      emptyStack.push(1).top.get should be(1)
    }
    "throw when popping" in new TestContext {
      emptyStack.pop should be(emptyStack)
    }

    "throw when topping" in new TestContext {
      emptyStack.top should be(None)
    }
  }

  "A non empty stack" should {
    "not be empty" in new TestContext {
      nonEmptyStack.isEmpty should be(false)
      nonEmptyStack.pop.pop.pop.pop.isEmpty should be(true)
    }
    "correctly insert" in new TestContext {
      nonEmptyStack.push(100).top.get should be(100)
      nonEmptyStack.push(100).pop.top.get should be(5)
    }

    "correctly pop" in new TestContext {
      nonEmptyStack.pop.top.get should be(3)
      nonEmptyStack.pop.pop.top.get should be(7)
    }

    "correctly top" in new TestContext {
      nonEmptyStack.top.get should be(5)
      nonEmptyStack.pop.top.get should be(3)
    }
  }

}
