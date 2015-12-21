//package com.ebusiello.fds.queue
//
//import com.ebusiello.fds.com.ebusiello.fds.queue.deque.Deque
//import org.specs2.mutable.Specification
//
//class DequeueSpec extends Specification {
//
//  val empty = new Deque[Int]()
//  val nonEmpty = new Deque[Int]().append(1).append(2)
//
//  "Deque" should {
//    "empty" in {
//      empty.isEmpty must beEqualTo(true)
//      empty.append(1).isEmpty must beEqualTo(false)
//      empty.prepend(1).isEmpty must beEqualTo(false)
//
//      nonEmpty.isEmpty must beEqualTo(false)
//      nonEmpty.popFirst.popFirst.isEmpty must beEqualTo(true)
//      nonEmpty.popLast.popLast.isEmpty must beEqualTo(true)
//
//    }
//    "insert" in {
//
//      empty.append(1).first must beEqualTo(1)
//      empty.append(1).append(2).first must beEqualTo(1)
//      empty.append(1).prepend(2).first must beEqualTo(2)
//      empty.append(1).prepend(2).popFirst.first must beEqualTo(1)
//      empty.append(2).append(4).popFirst.first must beEqualTo(4)
//
//    }
//    "last/first" in {
//      nonEmpty.last must beEqualTo(2)
//      nonEmpty.first must beEqualTo(1)
//    }
//  }
//
//
//}
