package com.ebusiello.fds.tree.binaryTree

import com.ebusiello.fds.tree.Tree

import scala.language.higherKinds

/**
 *
 */
private[binaryTree] abstract class AbstractBinaryTree[T, S[_], A[_]](head: AbstractBinaryNode[T]) extends BinaryCommon[T] with Tree {

  // -- need implementation

  def insert(mValue: T)(implicit ord: Ordering[T]): S[T]

  // TODO map should return a normal binary tree because the ordering clause could not be respected
  def map[V](f: T => V): A[V]


  // -- Generics

  /**
   * Whether a tree is empty or not depends on his head.
   */
  override def isEmpty: Boolean =
    head.isEmpty

  /**
   * Stringify a tree.
   */
  def stringify: String =
    head.toString

  /**
   * Tree insert (which basically is a head.insert).
   */
  def ++(mValue: T)(implicit ord: Ordering[T]): S[T] =
    insert(mValue)


  /**
   * Find an element of type T traversing the tree, if this node hold that element
   * return true, if it's smaller than the held value, recurse the left branch,
   * else the right.
   *
   *    2 -- search(3) -->   3 > 2                   2
   *   / \                   /   \                 /  \
   *  1  3                  1    3  -- search(3)  1   3 == 3 => true
   *
   */
  def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (isEmpty) false
    else head.find(mValue)

  /**
   * Fold a tree, apply a function to all the nodes of a tree and iteratively
   * build a new value of type S.
   *
   * @note is it possible to avoid compose?
   */
  def foldTree[P](z: P)(f: (P, T) => P)(compose: (P, P) => P): P =
    head.foldTree(z)(f)(compose)

  def reduceBinaryTree[P](default: P)(f: (P, T) => P)(compose: (P, P) => P): P =
    foldTree(default)(f)(compose)

}