package tree

import com.ebusiello.fds.tree.{DESC, ASC}
import com.ebusiello.fds.tree.binaryTree.balanced.{RightBalancedBinaryNode, LeftBalancedBinaryNode, LeftBalancedBinaryTree, RightBalancedBinaryTree}
import org.specs2.mutable.Specification

class BalancedTreeSpec extends Specification {

  lazy val left: LeftBalancedBinaryTree[Int] = LeftBalancedBinaryTree.fromColl(List(1, 2, 3, 4, 5))
  lazy val right: RightBalancedBinaryTree[Int] = RightBalancedBinaryTree.fromColl(List(1, 2, 3, 4, 5))
  lazy val empty: LeftBalancedBinaryTree[Int] = LeftBalancedBinaryTree.emptyTree[Int]

  "for a left balanced tree" should {
    "correctly be formed" in {

      left.getHead match {
        case h: LeftBalancedBinaryNode[Int] =>
          h.value must beEqualTo(1)
          h.left match {
            case l1: LeftBalancedBinaryNode[Int] =>
              l1.value must beEqualTo(2)
              l1.left match {
                case l2: LeftBalancedBinaryNode[Int] =>
                  l2.value must beEqualTo(4)
              }
          }
          h.right match {
            case l1: LeftBalancedBinaryNode[Int] =>
              l1.value must beEqualTo(3)
          }
      }

      right.getHead match {
        case h: RightBalancedBinaryNode[Int] =>
          h.value must beEqualTo(1)
          h.right match {
            case r1: RightBalancedBinaryNode[Int] =>
              r1.value must beEqualTo(2)
              r1.right match {
                case r2: RightBalancedBinaryNode[Int] =>
                  r2.value must beEqualTo(4)
              }
          }
          h.left match {
            case l1: RightBalancedBinaryNode[Int] =>
              l1.value must beEqualTo(3)
          }
      }

      empty.isEmpty must beEqualTo(true)
    }
    "not insert duplicates" in {
      left.insert(5).length must beEqualTo(left.length + 1)
      right.insert(5).length must beEqualTo(right.length + 1)
      empty.insert(5).length must not be equalTo(empty.length)
    }
    "correclty map" in {
      val leftmapped = left.map(_ * 5)
      leftmapped.find(25) must beEqualTo(true)
      leftmapped.find(10) must beEqualTo(true)
      leftmapped.find(5) must beEqualTo(true)
      leftmapped.find(15) must beEqualTo(true)

      val rightmapped = right.map(_ * 5)
      rightmapped.find(25) must beEqualTo(true)
      rightmapped.find(10) must beEqualTo(true)
      rightmapped.find(5) must beEqualTo(true)
      rightmapped.find(15) must beEqualTo(true)

      empty.map(_ * 5).isEmpty must beEqualTo(true)
    }
    "correctly sort" in {
      left.sort(ASC).getHead match {
        case h: LeftBalancedBinaryNode[Int] => h.value must beEqualTo(1)
      }
      left.sort(DESC).getHead match {
        case h: LeftBalancedBinaryNode[Int] => h.value must beEqualTo(5)
      }
      right.sort(ASC).getHead match {
        case h: RightBalancedBinaryNode[Int] => h.value must beEqualTo(1)
      }
      right.sort(DESC).getHead match {
        case h: RightBalancedBinaryNode[Int] => h.value must beEqualTo(5)
      }
      true must beEqualTo(true)
    }
    // TODO test to left and to right

  }


}
