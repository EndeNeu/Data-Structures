package com.ebusiello.data.structure.immutable.queues.queue

import com.ebusiello.data.structure.immutable.queues.QueueException
import org.scalatest.{Matchers, WordSpecLike}

class QueueArraySpec extends WordSpecLike with Matchers {

  trait TestContext {
    var queue = new QueueArray[Int](4)
  }

  "an immutable queue array" should {
    "correctly append" in new TestContext {

      queue.append(1).length() should be(1)
      queue.append(1).append(2).length() should be(2)
      queue.append(1).append(2).append(3).length() should be(3)
      queue.append(1).append(2).append(3).append(4).length() should be(4)

      intercept[QueueException] {
        queue.append(1).append(2).append(3).append(4).append(6)
      }

      queue.isEmpty should be(true)
      queue.top should be(None)
      intercept[QueueException] {
        queue.pop
      }
      queue.append(1).length() should be(1)
      queue.append(1).append(2).length() should be(2)
      queue.append(1).append(2).append(4).append(10).length() should be(4)

      val newQueue = queue.append(1).append(4).append(9)
      newQueue.length() should be(3)
      newQueue.top.get should be(1)
      newQueue.pop.top.get should be(4)
    }

    "not overflow" in new TestContext {
      intercept[QueueException] {
        queue.append(1).append(4).append(9).append(1).append(10)
      }
    }

    "correctly pop" in new TestContext {
      var newQueue = queue.append(1)
      newQueue.top.get should be(1)

      newQueue = queue.append(1).append(4).append(9)
      newQueue.top.get should be(1)

      newQueue = newQueue.pop
      newQueue.top.get should be(4)

      newQueue = newQueue.pop
      newQueue.top.get should be(9)

      newQueue = queue.append(1).append(4).append(9).append(10)
      newQueue.top.get should be(1)

      newQueue = newQueue.pop
      newQueue.top.get should be(4)

      newQueue = newQueue.pop
      newQueue.top.get should be(9)

      newQueue = newQueue.pop
      newQueue.top.get should be(10)

      // retry after cycling
      newQueue = queue.append(1).append(4).append(9).append(10)
      newQueue.top.get should be(1)

      newQueue = newQueue.pop
      newQueue.top.get should be(4)

      newQueue = newQueue.pop
      newQueue.top.get should be(9)

      newQueue = newQueue.pop
      newQueue.top.get should be(10)


      // retry with the pointer midway
      newQueue = queue.append(1).append(4).append(9).append(10).pop.pop

      newQueue.top.get should be(9)
      newQueue = newQueue.append(99).append(88)
      newQueue = newQueue.pop
      newQueue.top.get should be (10)

      newQueue = newQueue.pop
      newQueue.top.get should be (99)

      newQueue = newQueue.pop
      newQueue.top.get should be (88)



    }

    "correctly stringify" in new TestContext {
      var newQueue = queue.append(1).append(4).append(9)
      newQueue.stringify() should be("E |9|4|1| T")

      newQueue = queue.append(1).append(4).append(9).append(10)
      newQueue.stringify() should be("E |10|9|4|1| T")

      newQueue = queue.append(1).append(4)
      newQueue.stringify() should be("E |4|1| T")

      newQueue = queue.append(1)
      newQueue.stringify() should be("E |1| T")

      queue.stringify() should be("E | T")

    }
  }

}
