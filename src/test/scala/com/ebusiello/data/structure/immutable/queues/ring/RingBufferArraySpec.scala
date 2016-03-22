package com.ebusiello.data.structure.immutable.queues.ring

import java.util

import org.scalatest.{ Matchers, WordSpecLike }

class RingBufferArraySpec extends WordSpecLike with Matchers {

  trait TestContext {
    var ring = new RingBufferArray[Int](4)
  }

  "RingBufferArray" should {
    "correctly append" in new TestContext {
      ring.isEmpty should be(true)
      ring.isFull should be(false)
      ring.length() should be(0)

      ring = ring.append(1)
      ring.isEmpty should be(false)
      ring.isFull should be(false)
      ring.length() should be(1)

      ring = ring.append(2)
      ring = ring.append(3)
      ring = ring.append(4)
      ring.isEmpty should be(false)
      ring.isFull should be(true)
      ring.length() should be(4)
    }

    "correctly handle simple rings" in new TestContext {
      ring = ring.append(1)
      ring.top.get should be(1)

      ring = ring.pop
      ring.top should be(None)

      ring = ring.append(1)
      ring = ring.append(2)
      ring = ring.pop
      ring.top.get should be(1)
    }

    "correctly append when reaching the size" in new TestContext {
      ring = ring.append(1)
      ring = ring.append(2)
      ring = ring.append(3)
      ring = ring.append(4)
      ring = ring.append(5)

      ring.isEmpty should be(false)
      ring.isFull should be(true)
      ring.length() should be(4)

      ring.top.get should be(5)

      ring = ring.pop
      ring.top.get should be(4)

      ring = ring.pop
      ring.top.get should be(3)

      ring = ring.pop
      ring.top.get should be(2)

      ring = ring.pop
      ring.isEmpty should be(true)
      ring.top should be(None)

      // check that repopulating the ring does work after it has been emptied.
      ring = ring.append(1)
      ring = ring.append(2)
      ring = ring.append(3)
      ring = ring.append(4)
      ring = ring.append(5)
      ring = ring.append(6)

      ring.top.get should be(6)

      ring = ring.pop
      ring.top.get should be(5)

      ring = ring.pop
      ring.top.get should be(4)

      ring = ring.pop
      ring.top.get should be(3)
    }

    "correctly cyrcle the ring" in new TestContext {
      ring = ring.append(1)
      ring = ring.append(2)
      ring = ring.append(3)
      ring = ring.append(4)
      ring.stringify() should be("E |1|2|3|4| T")
      ring = ring.append(5)
      ring.stringify() should be("E |2|3|4|5| T")
      ring = ring.append(6)
      ring.stringify() should be("E |3|4|5|6| T")
      ring = ring.append(7)
      ring.stringify() should be("E |4|5|6|7| T")
      ring = ring.append(8)
      ring.stringify() should be("E |5|6|7|8| T")

      ring.top.get should be(8)
      ring = ring.pop
      ring.top.get should be(7)
      ring = ring.pop
      ring.top.get should be(6)
      ring = ring.pop
      ring.top.get should be(5)
    }

    "correctly cyrcle the ring twice" in new TestContext {
      ring = ring.append(1)
      ring = ring.append(2)
      ring = ring.append(3)
      ring = ring.append(4)
      ring.stringify() should be("E |1|2|3|4| T")

      ring = ring.append(5)
      ring = ring.append(6)
      ring = ring.append(7)
      ring = ring.append(8)
      ring.stringify() should be("E |5|6|7|8| T")

      ring = ring.append(9)
      ring = ring.append(10)
      ring = ring.append(11)
      ring = ring.append(12)
      ring.stringify() should be("E |9|10|11|12| T")

      ring.top.get should be(12)
      ring = ring.pop
      ring.top.get should be(11)
      ring = ring.pop
      ring.top.get should be(10)
      ring = ring.pop
      ring.top.get should be(9)
    }
  }
}

