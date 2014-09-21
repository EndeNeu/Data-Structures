package tree

import com.ebusiello.fds.tree.binaryTree.balanced.LeftBalancedBinaryTree
import com.ebusiello.fds.tree.binaryTree.search.{BinarySearchNode, BinarySearchTree}
import org.specs2.mutable.Specification


class BinaryTreeSpec extends Specification {

  val values = List(3,2,4)
  val emptyBinaryTree = BinarySearchTree.emptyTree[Int]
  val binaryTree: BinarySearchTree[Int] = BinarySearchTree.fromColl(values)
  val nonEmpty: BinarySearchTree[Int] = BinarySearchTree.fromColl(values)

  "For BinaryTree" should {
    "be empty" in {
      emptyBinaryTree.isEmpty must beEqualTo(true)
      (emptyBinaryTree ++ 3).isEmpty must beEqualTo(false)
    }
    "insert should work correctly " in {
      val p = nonEmpty.getHead.map(n => {
        n.value must beEqualTo(3)
        n.left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(2)
        n.right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(4)
      })

      nonEmpty.isEmpty must beEqualTo(false)
      val listed = nonEmpty.foldTree(List.empty[Int])(_ :+ _)(_ ++ _)
      listed.contains(3) must beEqualTo(true)
      listed.contains(2) must beEqualTo(true)
      listed.contains(4) must beEqualTo(true)

    }
    "correctly find" in {
      emptyBinaryTree.find(1) must beEqualTo(false)
      binaryTree.find(3) must beEqualTo(true)
      binaryTree.find(2) must beEqualTo(true)
      binaryTree.find(4) must beEqualTo(true)
      binaryTree.find(6) must beEqualTo(false)
    }
    "correctly map" in {
      val mappedBinaryTree: LeftBalancedBinaryTree[Int] = binaryTree.map(_ * 3)
      mappedBinaryTree.find(3 * 3) must beEqualTo(true)
      mappedBinaryTree.find(2 * 3) must beEqualTo(true)
      mappedBinaryTree.find(4 * 3) must beEqualTo(true)
      mappedBinaryTree.find(6 * 3) must beEqualTo(false)
    }
  }
}
