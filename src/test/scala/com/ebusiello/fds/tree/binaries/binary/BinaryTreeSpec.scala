package com.ebusiello.fds.tree.binaries.binary

import org.scalatest.{ Matchers, WordSpecLike }

class BinaryTreeSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyTree = BinaryTree.emptyTree[Int]
  }

  "Binary com.ebusiello.fds.tree" should {
    "correctly insert" in new TestContext {
      val nonEmptyTree = emptyTree.insert(1).insert(2).insert(3)
      nonEmptyTree.find(1) should be(true)
      nonEmptyTree.find(2) should be(true)
      nonEmptyTree.find(3) should be(true)
      nonEmptyTree.find(4) should be(false)
      nonEmptyTree.length should be(3)
      nonEmptyTree.depth should be(3)

      emptyTree.find(0) should be(false)
    }

    "avoid duplicated inserts" in new TestContext {
      val nonEmptyTree = emptyTree.insert(1).insert(1)
      nonEmptyTree.length should be(1)
      nonEmptyTree.depth should be(1)
    }

    "correctly map" in new TestContext {
      val nonEmptyTree = emptyTree.insert(2).insert(1).insert(3)
      val mapped = nonEmptyTree.map(x => x * 2)
      mapped.find(2) should be(true)
      mapped.find(4) should be(true)
      mapped.find(6) should be(true)
      mapped.find(8) should be(false)

      val t1 = emptyTree.insert(1).insert(-1).insert(2).map(_ * -1)

      t1.head.value should be(1)
      t1.head.left.value should be(-2)
      t1.head.left.right.value should be(-1)
    }

    "correctly remove an element" in new TestContext {
      val nonEmptyTree = emptyTree.insert(2).insert(1).insert(5).insert(4).insert(6)
      nonEmptyTree.remove(4).head.right.left shouldBe a[EmptyBinaryNode[_]]

      emptyTree.remove(0).head shouldBe a[EmptyBinaryNode[_]]
    }

    "correctly return empty" in new TestContext {
      emptyTree.isEmpty should be(true)
      emptyTree.insert(1).isEmpty should be(false)
    }

    "correclty fold" in new TestContext {
      emptyTree.insert(1).insert(-1).insert(2).foldTree(0)((acc, curr) => acc + curr)((a1, a2) => a1 + a2) should be(2)
    }
  }

}
