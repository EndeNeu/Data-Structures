package com.ebusiello.fds.tree.binaries.binary

import org.scalatest.{ Matchers, WordSpecLike }

class BinaryTreeSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val emptyTree = BinarySearchTree.emptyTree[Int]
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

    "correctly map and rebalance" in new TestContext {
      val nonEmptyTree = emptyTree.insert(2).insert(1).insert(3)
      val mapped = nonEmptyTree.map(x => x * 2)
      mapped.find(2) should be(true)
      mapped.find(4) should be(true)
      mapped.find(6) should be(true)
      mapped.find(8) should be(false)

      val t1 = emptyTree.insert(1).insert(-1).insert(2).map(_ * -1)

      t1.head.value should be(-1)
      t1.head.left.value should be(-2)
      t1.head.right.value should be(1)

      // rebalance a totally unbalanced tree.
      val n3 = BinarySearchTree.fromColl[Int, List[Int]](List(5, 4, 6, 2, 7)).map(_ * -1)
      n3.head.value should be(-5)
      n3.head.left.value should be(-6)
      n3.head.left.left.value should be(-7)
      n3.head.right.value should be(-4)
      n3.head.right.right.value should be(-2)

      // rebalance a node full of duplicates
      val n4 = BinarySearchTree.fromColl[Int, List[Int]](List(5, 4, 6, 2, 7)).map(_ % 2)
      n4.head.value should be(0)
      n4.head.right.value should be(1)
      n4.head.left shouldBe a[EmptyBinarySearchNode[_]]
    }

    "correctly remove an element" in new TestContext {
      val nonEmptyTree = emptyTree.insert(50).insert(100).insert(30).insert(20).insert(40).insert(35).insert(45).insert(37)

      // https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeletenotrightchildexample.jpg
      val n1 = nonEmptyTree.remove(30)
      n1.head.value should be(50)
      n1.head.right.value should be(100)
      n1.head.left.value should be(35)
      n1.head.left.left.value should be(20)
      n1.head.left.right.value should be(40)
      n1.head.left.right.left.value should be(37)
      n1.head.left.right.right.value should be(45)

      emptyTree.remove(0).head shouldBe a[EmptyBinarySearchNode[_]]

      //https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeleteleafexample.jpg
      val tree = BinarySearchTree.fromColl[Int, List[Int]](List(50, 90, 100, 30, 20, 40))
      val t2 = tree.remove(40)
      t2.head.value should be(50)
      t2.head.right.value should be(90)
      t2.head.right.right.value should be(100)
      t2.head.left.value should be(30)
      t2.head.left.left.value should be(20)
      t2.find(40) should be(false)

      // https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeleteonechildexample.jpg
      val t3 = tree.remove(90)
      t3.head.value should be(50)
      t3.head.right.value should be(100)
      t3.head.right.right shouldBe a[EmptyBinarySearchNode[_]]
      t3.head.left.value should be(30)
      t3.head.left.left.value should be(20)
      t3.head.left.right.value should be(40)

      // https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeleterightchildexample.jpg
      val t4 = t3.remove(30).insert(45)
      t4.head.value should be(50)
      t4.head.right.value should be(100)
      t4.head.left.value should be(40)
      t4.head.left.left.value should be(20)
      t4.head.left.right.value should be(45)

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
