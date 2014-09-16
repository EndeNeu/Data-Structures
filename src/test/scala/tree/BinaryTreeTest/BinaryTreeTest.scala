package tree.BinaryTreeTest

import com.ebusiello.fds.tree.binaryTree.BinaryTree
import org.specs2.mutable.Specification


class BinaryTreeTest extends Specification {

  val emptyBinaryTree = BinaryTree.empty[Int]
  val binaryTree: BinaryTree[Int] = emptyBinaryTree ++ 3 ++ 4 ++ 5

  "Empty BinaryTree" should {
    "be empty" in {
      emptyBinaryTree.isEmpty must beEqualTo(true)
      (emptyBinaryTree ++ 3).isEmpty must beEqualTo(false)
    }
    "correctly find" in {
      emptyBinaryTree.find(1) must beEqualTo(false)
      binaryTree.find(3) must beEqualTo(true)
      binaryTree.find(4) must beEqualTo(true)
      binaryTree.find(5) must beEqualTo(true)
      binaryTree.find(6) must beEqualTo(false)
    }
    "correctly map" in {
      val mappedBinaryTree: BinaryTree[Int] = binaryTree.map(_ * 3)
      println(binaryTree.stringify)

      println(binaryTree.sort.stringify)

      val folded: Int = mappedBinaryTree.foldTree(0)((acc, curr) => acc + curr)((s1, s2) => s1 + s2)

      println("---------------")
      println(mappedBinaryTree.stringify)
      println(folded)

      val folded2 = binaryTree.foldTree(List[Int]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)

      println("---------------")
      folded2.foreach(println)

      mappedBinaryTree.find(3 * 3) must beEqualTo(true)
      mappedBinaryTree.find(4 * 3) must beEqualTo(true)
      mappedBinaryTree.find(5 * 3) must beEqualTo(true)
      mappedBinaryTree.find(6 * 3) must beEqualTo(false)
    }
  }
}
