package com.ebusiello.fds.tree.binaryTree.searchTree

import com.ebusiello.fds.tree.SortableTree
import com.ebusiello.fds.tree.binaryTree.{AbstractBinaryNode, AbstractBinaryTree, EmptyNode}
import com.ebusiello.fds.tree.binaryTree.balancedTree.BalancedBinaryTree

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
class BinarySearchTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T, BinarySearchTree, BalancedBinaryTree](head) with SortableTree[T, BinarySearchTree] {


  /**
   * Return the tree head wrapped in an option, if the head is an empty node return None.
   */
  def getHead: Option[BinaryNode[T]] = head match {
    case node: BinaryNode[T] => Some(node)
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
   *       |                        |                           |
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
    if (isEmpty) new BinarySearchTree[T](new BinaryNode[T](mValue, new EmptyNode, new EmptyNode))
    else new BinarySearchTree[T](head.insert(mValue))

  /**
   * Map a function over a tree, this returns a new tree which is not a search tree
   * this is due to the fact that mapping a function could break the left-right law
   * (eg, if we map -1 on all the nodes)
   */
  override def map[V](f: T => V): BalancedBinaryTree[V] =
    if (isEmpty) new BalancedBinaryTree[V](new EmptyNode)
    else new BalancedBinaryTree[V](head.map(f))

  /**
   * Sort a search tree, note that sorting will always yield a right tree:
   *
   *    2 -- sort -->          1
   *   / \                    / \
   *  1  3                   E  2
   *                           / \
   *                          E  3
   *                            / \
   *                           E  E
   */
  override def sort(implicit ord: Ordering[T]): BinarySearchTree[T] = {
    foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)
      //.filter(node => node.isInstanceOf[EmptyNode[T]])
      .sorted
      .foldLeft(BinarySearchTree.emptyTree[T])((tree, value) => tree.insert(value))
  }

  // TODO
  override def reduceBinaryTree[S](f: (T, T) => T): T = ???
}

object BinarySearchTree {

  /**
   * Easily create an empty tree.
   */
  def emptyTree[T]: BinarySearchTree[T] = new BinarySearchTree[T](new EmptyNode[T])
}