package com.ebusiello.fds.tree.binaries

import com.ebusiello.fds.tree.generic.tree.{ OrderableTree, RemoveableTree, Tree }
import play.api.libs.json.Writes

import scala.language.higherKinds

/**
 * Generic implementation for a binary com.ebusiello.fds.tree, basically a wrapper around the root element (head),
 * defer operations to the head element.
 */
private[tree] trait GenericBinaryTree[T, S[_], N[T] <: GenericBinaryNode[T, N]] extends Tree[T] with OrderableTree[T, S] with RemoveableTree[T, S] {

  val head: N[T]

  /**
   * Whether a com.ebusiello.fds.tree is empty or not depends on his head.
   */
  override def isEmpty: Boolean =
    head.isEmpty

  /**
   * Stringify a com.ebusiello.fds.tree.
   */
  override def stringify: String =
    head.stringify

  /**
   * Find an element of type T traversing the com.ebusiello.fds.tree, if this node hold that element
   * return true, if it's smaller than the held value, recurse the left branch,
   * else the right.
   *
   *    2 -- search(3) -->   3 > 2                   2
   *   / \                   /   \                 /  \
   *  1  3                  1    3  -- search(3)  1   3 == 3 => true
   *
   */
  override def find(value: T)(implicit ord: Ordering[T]): Boolean =
    if (isEmpty) false
    else head.find(value)

  /**
   * Fold a com.ebusiello.fds.tree, apply a function to all the nodes of a com.ebusiello.fds.tree and iteratively
   * build a new value of type S.
   *
   * @note is it possible to avoid compose?
   */
  override def foldTree[P](z: P)(f: (P, T) => P)(compose: (P, P) => P): P =
    head.foldTree(z)(f)(compose)

  /**
   * Calculate the depth of the com.ebusiello.fds.tree
   *
   *     1    <-- depth 1
   *    /\
   *   2 E   <-- depth 2
   *  /\
   * 1 4    <-- depth 3
   */
  override def depth: Int =
    head.depth

  /**
   * Calculate the number of elements in a com.ebusiello.fds.tree
   */
  override def length: Int =
    head.length

}