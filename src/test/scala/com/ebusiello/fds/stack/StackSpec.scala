//package com.ebusiello.fds.stack
//
//import com.ebusiello.fds.com.ebusiello.fds.stack.Stack
//import org.specs2.mutable.Specification
//
//class StackSpec extends Specification {
//
//  val emptyStack = Stack.empty[Int]
//  val nonEmptyStack = new Stack[Int]().push(1).push(7).push(3).push(5)
//
//  "Stack" should {
//    "be empty" in {
//      emptyStack.isEmpty must beEqualTo(true)
//      emptyStack.push(3).isEmpty must beEqualTo(false)
//      nonEmptyStack.isEmpty must beEqualTo(false)
//      nonEmptyStack.pop.pop.pop.pop.isEmpty must beEqualTo(true)
//    }
//    "insert" in {
//      emptyStack.push(1).top must beEqualTo(1)
//      nonEmptyStack.push(10).top must beEqualTo(10)
//      nonEmptyStack.top must beEqualTo(5)
//    }
//    "pop" in {
//      nonEmptyStack.pop.top must beEqualTo(3)
//      nonEmptyStack.pop.pop.top must beEqualTo(7)
//    }
//  }
//
//}
