//package com.ebusiello.fds.tree.binaryTree.balanced.right
//
//import com.ebusiello.fds.tree.binaryTree.balanced.generic.{ GenericBalancedBinaryTree, GenericBalancedBinaryNode }
//import com.ebusiello.fds.tree.binaryTree.balanced.left.LeftBalancedBinaryTree
//import com.ebusiello.fds.tree.binaryTree.balanced.GenericBalancedBinaryTree
//import com.ebusiello.fds.com.ebusiello.fds.tree.generic.com.ebusiello.fds.tree.{ ASC, DESC, Direction, SortableTree }
//
///**
// * A variation of a AVL com.ebusiello.fds.tree (Adelson-Velskii and Landis com.ebusiello.fds.tree http://en.wikipedia.org/wiki/AVL_tree), the balancing has preference towards the right side.
// */
//final class RightBalancedBinaryTree[T](head: GenericBalancedBinaryNode[T]) extends GenericBalancedBinaryTree[T, RightBalancedBinaryTree](head) with SortableTree[T, RightBalancedBinaryTree] {
//
//  override def toRight: RightBalancedBinaryTree[T] =
//    new RightBalancedBinaryTree[T](head.toRight)
//
//  override def toLeft: LeftBalancedBinaryTree[T] =
//    new LeftBalancedBinaryTree[T](head.toLeft)
//
//  override def map[V](f: (T) => V): RightBalancedBinaryTree[V] = head match {
//    case balancedNode: GenericBalancedBinaryNode[T] => new RightBalancedBinaryTree[V](balancedNode.map(f))
//  }
//
//  override def insert(mValue: T)(implicit ord: Ordering[T]): RightBalancedBinaryTree[T] =
//    if (isEmpty) new RightBalancedBinaryTree[T](new RightGenericBalancedBinaryNode[T](mValue, new RightBalancedEmptyNode[T], new RightBalancedEmptyNode[T]))
//    else new RightBalancedBinaryTree[T](head.insert(mValue))
//
//  new Ordering[Int] {
//    override def compare(x: Int, y: Int): Int = if (x > y) x else y
//  }
//
//  override def sort(direction: Direction)(implicit ord: Ordering[T]): RightBalancedBinaryTree[T] = {
//    val folded = foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)
//    direction match {
//      case DESC => RightBalancedBinaryTree.fromColl(folded.sorted(ord.reverse))
//      case ASC => RightBalancedBinaryTree.fromColl(folded.sorted)
//    }
//  }
//}
//
//object RightBalancedBinaryTree {
//
//  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): RightBalancedBinaryTree[T] =
//    coll.foldLeft(RightBalancedBinaryTree.emptyTree[T])(_.insert(_))
//
//  /**
//   * Easily create an empty tree.
//   */
//  def emptyTree[T] = new RightBalancedBinaryTree[T](new RightBalancedEmptyNode[T])
//}