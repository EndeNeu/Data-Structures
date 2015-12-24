//package com.ebusiello.fds.queue
//
//import com.ebusiello.fds.com.ebusiello.fds.queue.com.ebusiello.fds.queue.Queue
//import org.specs2.mutable.Specification
//
//class QueueSpec extends Specification {
//
//  val emptyQueue = new Queue[Int]()
//  val nonEmpty: Queue[Int] = Queue[Int](1).enqueue(2).enqueue(5).enqueue(7).enqueue(8)
//
//  "Queue" should {
//
//    "empty" in {
//      emptyQueue.isEmpty must beEqualTo(true)
//      nonEmpty.isEmpty must beEqualTo(false)
//    }
//    "insert" in {
//      emptyQueue.enqueue(1).isEmpty must beEqualTo(false)
//      emptyQueue.enqueue(1).top must beEqualTo(1)
//      emptyQueue.enqueue(1).dequeue.isEmpty must beEqualTo(true)
//
//      nonEmpty.enqueue(10).top must beEqualTo(1)
//      nonEmpty.enqueue(10).dequeue.top must beEqualTo(2)
//
//      nonEmpty.dequeue.dequeue.dequeue.dequeue.top must beEqualTo(8)
//      nonEmpty.dequeue.dequeue.dequeue.top must beEqualTo(7)
//      nonEmpty.dequeue.dequeue.top must beEqualTo(5)
//
//    }
//  }
//
//}