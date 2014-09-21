package com.ebusiello.fds.tree.binaryTree.search

import com.ebusiello.fds.tree.{ASC, DESC, Direction, SortableTree}
import com.ebusiello.fds.tree.binaryTree.balanced._

/**
 * A binary tree is composed of nodes, a node can be empty (emptyNode) or hold a value (node). A node
 * holds a value and a reference to the left and right node, the value they hold is greater than the value
 * on the left branch and smaller then the one on the right branch:
 *
 * possible tree       impossible tree
 *    2                      2
 *   / \                    / \
 *  1  3                   3  1
 *
 *  This principle help in the search and insertion, if we search for a value V we check the current node
 *  if it's the value we return true, if it's smaller we look in the left part, else in the right part.
 *  The same goes for insert.
 */
final class BinarySearchTree[T](val head: AbstractBinarySearchNode[T]) extends AbstractBinarySearchTree[T](head) with SortableTree[T, BinarySearchTree] {


  /**
   * Return the tree head wrapped in an option, if the head is an empty node return None.
   */
  def getHead: Option[BinarySearchNode[T]] = head match {
    case node: BinarySearchNode[T] => Some(node)
    case _ => None
  }

  /**
   * Insert an element of type T in a tree, if the tree is empty
   * return a new tree with one node which will hold the value,
   * if the tree is not empty delegate the insert to the head
   *
   * Empty tree
   *
   *     Tree  -- insert(v) -->   Tree
   *       |                        |
   *     Empty                     N(v)
   *                              /   \
   *                             E    E
   *
   * Non empty tree
   *
   *     Tree   -- insert(v) -->   Tree                       Tree
   *       |                        |                          |
   *     N(v)                      N(v)  -- insert(v) -->     N(v)
   *    /   \                     /   \                      /   \
   *   E    E                    E    E                    N(v)  E
   *                                                      /   \
   *                                                     E    E
   *
   * @param mValue
   * @param ord
   * @return
   */
  override def insert(mValue: T)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    if (isEmpty) new BinarySearchTree[T](new BinarySearchNode[T](mValue, new EmptyBinarySearchNode, new EmptyBinarySearchNode))
    else new BinarySearchTree[T](head.insert(mValue))

  /**
   * Map a function over a tree, this returns a new tree which is not a search tree
   * this is due to the fact that mapping a function could break the left-right law
   * (eg, if we map -1 on all the nodes)
   */
  override def map[V](f: T => V): LeftBalancedBinaryTree[V] =
    if (isEmpty) new LeftBalancedBinaryTree[V](new LeftBalancedEmptyNode[V])
    else head match {
      case searchNode: BinarySearchNode[T] => new LeftBalancedBinaryTree[V](searchNode.map(f))
    }

  /**
   * Sort a search tree, note that sorting will always yield a right tree:
   *
   *    2 -- sort -->      1
   *   / \                / \
   *  1  3               E  2
   *                       / \
   *                      E  3
   *                        / \
   *                       E  E
   */
  override def sort(direction: Direction = DESC)(implicit ord: Ordering[T]): BinarySearchTree[T] = {
    val folded = foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2).sorted
    direction match {
      case DESC => BinarySearchTree.fromColl(folded.sorted)
      case ASC => BinarySearchTree.fromColl(folded.sorted(ord.reverse))
    }
  }

}

object BinarySearchTree {

  def fromColl[T, S <: TraversableOnce[T]](coll: S)(implicit ord: Ordering[T]): BinarySearchTree[T] =
    coll.foldLeft(BinarySearchTree.emptyTree[T])(_.insert(_))

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: BinarySearchTree[T] = new BinarySearchTree[T](new EmptyBinarySearchNode[T])
}