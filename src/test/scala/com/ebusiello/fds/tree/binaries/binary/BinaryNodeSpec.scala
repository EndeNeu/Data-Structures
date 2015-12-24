package com.ebusiello.fds.tree.binaries.binary

import org.scalatest.{ Matchers, WordSpecLike }

class BinaryNodeSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyNode = new EmptyBinarySearchNode[Int]
    val node = new BinarySearchNode[Int](0, new EmptyBinarySearchNode[Int], new EmptyBinarySearchNode[Int])
  }

  "EmptyBinaryNode" should {
    "correclty insert" in new TestContext {
      val n1 = emptyNode.insert(1)
      n1.isEmpty should be(false)
      n1.left shouldBe a[EmptyBinarySearchNode[_]]
      n1.right shouldBe a[EmptyBinarySearchNode[_]]
      n1.value should be(1)

    }

    "correctly map" in new TestContext {
      emptyNode.map(identity) shouldBe a[EmptyBinarySearchNode[_]]

    }

    "return empty" in new TestContext {
      emptyNode.isEmpty should be(true)
      emptyNode.nonEmpty should be(false)
    }

    "not find" in new TestContext {
      emptyNode.find(1) should be(false)
    }

    "correctly remove" in new TestContext {
      emptyNode.remove(1) shouldBe a[EmptyBinarySearchNode[_]]
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

    "correctly stringify" in new TestContext {
      emptyNode.stringify should be("E")
    }
  }

  "BinaryNode" should {
    "correctly insert" in new TestContext {
      val n2 = node.insert(2)
      n2.value should be(0)
      n2.right.value should be(2)
      n2.right.left shouldBe a[EmptyBinarySearchNode[_]]
      n2.right.right shouldBe a[EmptyBinarySearchNode[_]]
      n2.left shouldBe a[EmptyBinarySearchNode[_]]
      n2.insert(-1).left.value should be(-1)
    }

    "correctly map and rebalance" in new TestContext {
      node.map(_ + 1).value should be(1)

      val n1 = node.insert(2).insert(-1).map(_ + 2)
      n1.value should be(2)
      n1.right.value should be(4)
      n1.left.value should be(1)

      val n2 = node.insert(-2).insert(1).map(_ * -1)
      n2.value should be(0)
      n2.right.value should be(-1)
      n2.left.value should be(2)

      /**
       *         5    map _ * -1 ->  -5
       *       /  \                 /  \
       *      4   6               -4   -6
       *     / \ / \              / \  / \
       *    2  E E  7           -2  E E  -7
       *
       *    rebalance
       *
       *
       *
       *
       */

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
      node.remove(0) shouldBe a[EmptyBinarySearchNode[_]]
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

    "correctly calculate the left and right depth" in new TestContext {
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 9, 11)).head.relativeDepth should be(2)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 9, 11)).head.left.relativeDepth should be(1)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 9, 11)).head.right.relativeDepth should be(1)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 9, 11)).head.left.left.relativeDepth should be(0)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 9, 11)).head.right.right.relativeDepth should be(0)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 9, 11, 8)).head.relativeDepth should be(3)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 8, 11, 7, 9)).head.relativeDepth should be(3)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 8, 12, 11)).head.left.relativeDepth should be(1)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 8, 12, 11)).head.right.relativeDepth should be(2)
      BinarySearchTree.fromColl[Int, List[Int]](List(10, 8, 12, 11, 13)).head.right.relativeDepth should be(2)
    }
  }

}
