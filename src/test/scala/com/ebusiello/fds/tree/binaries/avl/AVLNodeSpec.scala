package com.ebusiello.fds.tree.binaries.avl

import com.ebusiello.fds.tree.binaries.avl.generic.{ AVLNode, EmptyAVLNode }
import org.scalatest.{ Matchers, WordSpecLike }

class AVLNodeSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyNode = new EmptyAVLNode[Int]
    val node = new AVLNode[Int](0, new EmptyAVLNode[Int], new EmptyAVLNode[Int])
  }

  "EmptyAVLNode" should {
    "correclty insert" in new TestContext {
      val n1 = emptyNode.insert(1)
      n1.isEmpty should be(false)
      n1.left shouldBe a[EmptyAVLNode[_]]
      n1.right shouldBe a[EmptyAVLNode[_]]
      n1.value should be(1)
    }

    "correctly map" in new TestContext {
      emptyNode.map(identity) shouldBe a[EmptyAVLNode[_]]

    }

    "return empty" in new TestContext {
      emptyNode.isEmpty should be(true)
      emptyNode.nonEmpty should be(false)
    }

    "not find" in new TestContext {
      emptyNode.find(1) should be(false)
    }

    "correctly remove" in new TestContext {
      emptyNode.remove(1) shouldBe a[EmptyAVLNode[_]]
    }

    "correctly return the lenght" in new TestContext {
      emptyNode.length should be(0)
    }

    "correctly return the depth" in new TestContext {
      emptyNode.depth should be(0)
    }

    "correctly fold" in new TestContext {
      emptyNode.foldTree(0)((acc, curr) => acc + curr)((a1, a2) => a1 + a2) should be(0)
    }
  }

  "AVLNode" should {
    "correctly insert" in new TestContext {
      val n2 = node.insert(2)
      n2.value should be(0)
      n2.left.value should be(2)
      n2.right shouldBe a[EmptyAVLNode[_]]
      n2.insert(-1).right.value should be(-1)

      val n3: AVLNode[Int] = n2.insert(-1).insert(3).insert(4).insert(5).insert(6)
      n3.value should be(0)
      n3.left.value should be(2)
      n3.right.value should be(-1)
      n3.left.left.value should be(3)
      n3.left.right.value should be(4)
      n3.right.left.value should be(5)
      n3.right.right.value should be(6)
    }

    "correctly map" in new TestContext {
      node.map(_ + 1).value should be(1)

      val n1 = node.insert(2).insert(-1).map(_ + 2)
      n1.value should be(2)
      n1.left.value should be(4)
      n1.right.value should be(1)

      val n2 = node.insert(-2).insert(1).map(_ * -1)
      n2.value should be(0)
      n2.right.value should be(-1)
      n2.left.value should be(2)
    }

    "return non empty" in new TestContext {
      node.isEmpty should be(false)
      node.nonEmpty should be(true)
    }

    "not find" in new TestContext {
      node.find(1) should be(false)
      node.find(0) should be(true)
    }

    "correctly remove" in new TestContext {
      node.remove(0) shouldBe a[EmptyAVLNode[_]]
    }

    "correctly return the lenght" in new TestContext {
      node.length should be(1)
    }

    "correctly return the depth" in new TestContext {
      node.depth should be(1)
    }

    "correctly fold" in new TestContext {
      node.foldTree(0)((acc, curr) => acc + curr)((a1, a2) => a1 + a2) should be(0)
    }
  }

}