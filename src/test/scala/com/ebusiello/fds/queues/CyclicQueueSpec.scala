//package com.ebusiello.fds.queue
//
//import com.ebusiello.fds.com.ebusiello.fds.queue.cyclic.CyclicQueue
//import org.specs2.mutable.Specification
//
//class CyclicQueueSpec extends Specification {
//
//  val empty = new CyclicQueue[Int](3)
//  val nonEmpty = new CyclicQueue[Int](3).enqueue(2).enqueue(1)
//
//  "CyclicQueue" should {
//
//    "empty" in {
//      empty.isEmpty must beEqualTo(true)
//      empty.enqueue(1).isEmpty must beEqualTo(false)
//      nonEmpty.isEmpty must beEqualTo(false)
//      nonEmpty.dequeue.dequeue.isEmpty must beEqualTo(true)
//    }
//    "enqueue" in {
//      empty.enqueue(1).enqueue(2).enqueue(3).top must beEqualTo(1)
//      empty.enqueue(1).enqueue(2).dequeue.top must beEqualTo(2)
//      empty.enqueue(1).enqueue(2).enqueue(3).dequeue.dequeue.top must beEqualTo(3)
//      empty.enqueue(1).enqueue(2).enqueue(3).enqueue(4).top must beEqualTo(2)
//      empty.enqueue(1).enqueue(2).enqueue(3).enqueue(4).dequeue.dequeue.top must beEqualTo(4)
//    }
//    "last" in {
//      nonEmpty.last must beEqualTo(1)
//    }
//
//  }
//
//
//}
