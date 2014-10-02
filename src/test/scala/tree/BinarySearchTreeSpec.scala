package tree

import com.ebusiello.fds.tree.binaryTree.balanced.LeftBalancedBinaryTree
import com.ebusiello.fds.tree.binaryTree.search.{BinarySearchNode, BinarySearchTree}
import org.specs2.mutable.Specification


class BinarySearchTreeSpec extends Specification {

  val values = List(3,2,4)
  val emptyBinaryTree = BinarySearchTree.emptyTree[Int]
  val binaryTree: BinarySearchTree[Int] = BinarySearchTree.fromColl(values)
  val nonEmpty: BinarySearchTree[Int] = BinarySearchTree.fromColl(values)

  "BinaryTree" should {
    "corretly be formed" in {

      binaryTree.getHead match {
        case bn: BinarySearchNode[Int] =>
          bn.value must beEqualTo(3)
          bn.left match {
            case bl: BinarySearchNode[Int] => bl.value must beEqualTo(2)
          }
          bn.right match {
            case bl: BinarySearchNode[Int] => bl.value must beEqualTo(4)
          }
      }
    }
    "be empty" in {
      emptyBinaryTree.isEmpty must beEqualTo(true)
      (emptyBinaryTree ++ 3).isEmpty must beEqualTo(false)
    }
    "insert should work correctly " in {
      val p = nonEmpty.getHead match {
        case h: BinarySearchNode[Int] =>
          h.value must beEqualTo(3)
          h.left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(2)
          h.right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(4)
      }

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
    "delete" in {

      val tree = BinarySearchTree.fromColl[Int, List[Int]](List(1, -10, 10, 6, 5, 9, 15, 14, 20))

      val j = tree.delete(10)

      tree.delete(10).getHead match {
        case head: BinarySearchNode[Int] =>
          head.value must beEqualTo(1)
          head.right match {
            case right: BinarySearchNode[Int] =>
              right.value must beEqualTo(6)
          }
      }

      val tree2 = BinarySearchTree.fromColl[Int, List[Int]](List(1, -10, 10, 6, 9, 15, 14, 20))

      tree2.delete(10).getHead match {
        case head: BinarySearchNode[Int] =>
          head.value must beEqualTo(1)
          head.right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(9)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(6)
      }

      val tree3 = BinarySearchTree.fromColl[Int, List[Int]](List(1, -10, 10, 6, 7, 8, 15, 14, 20))

      tree3.delete(10).getHead match {
        case head: BinarySearchNode[Int] =>
          head.value must beEqualTo(1)
          head.right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(7)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(6)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(8)
      }

      val tree4 = BinarySearchTree.fromColl[Int, List[Int]](List(1, -10, 10, 9, 8, 7))

      tree4.delete(10).getHead match {
        case head: BinarySearchNode[Int] =>
          head.value must beEqualTo(1)
          head.right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(9)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(8)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(7)
      }

      val tree5 = BinarySearchTree.fromColl[Int, List[Int]](List(1, -10, 10, 8, 15, 9, 7))

      val bbb = tree5.delete(10)

      tree5.delete(10).getHead match {
        case head: BinarySearchNode[Int] =>
          head.value must beEqualTo(1)
          head.right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(8)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(7)
          head.right.asInstanceOf[BinarySearchNode[Int]].left.asInstanceOf[BinarySearchNode[Int]].right.asInstanceOf[BinarySearchNode[Int]].value must beEqualTo(9)
      }

       tree.delete(10).find(10) must beEqualTo(false)
    }
  }
}
