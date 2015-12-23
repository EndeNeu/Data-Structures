package com.ebusiello.fds.tree.binaries.avl.generic

import com.ebusiello.fds.tree.binaries.GenericBinaryNode
import com.ebusiello.fds.tree.generic.node.{ OrderableNode, RotableNode }

class AVLNode[T](val value: T, val left: AVLNode[T], val right: AVLNode[T]) extends GenericBinaryNode[T, AVLNode] with OrderableNode[T, AVLNode] with RotableNode[T, AVLNode] {

  override def insert(newValue: T)(implicit ord: Ordering[T]): AVLNode[T] = this match {
    case AVLNode(v, _, _) if v == newValue =>
      this
    case AVLNode(v, l: EmptyAVLNode[T], r: AVLNode[T]) =>
      new AVLNode(value, left.insert(newValue), right)
    case AVLNode(v, l: AVLNode[T], r: EmptyAVLNode[T]) =>
      new AVLNode(value, left, right.insert(newValue))
    case AVLNode(v, l: EmptyAVLNode[T], r: EmptyAVLNode[T]) =>
      new AVLNode(value, left.insert(newValue), right)
    case AVLNode(v, l: AVLNode[T], r: AVLNode[T]) =>
      if (left.leftRelativeDepth <= right.rightRelativeDepth) new AVLNode(value, left.insert(newValue), right)
      else new AVLNode(value, left, right.insert(newValue))
  }

  override def remove(v: T): AVLNode[T] = {
    if (v == value) {
      // if this node is to be removed and the left leaf is non empty pick that to replace this node
      if (left.nonEmpty && right.isEmpty) new AVLNode[T](left.value, new EmptyAVLNode[T], right)
      // else do the same with the right
      else if (right.nonEmpty && left.isEmpty) new AVLNode[T](right.value, left, new EmptyAVLNode[T])
      // else if both are empty just return an empty node.
      else if (right.isEmpty && left.isEmpty) new EmptyAVLNode[T]
      // if both are not empty, look for the first right node with a left empty node
      // and rotate the tree, after that delete the value who has rotated.
      else rotateNode()
    } else new AVLNode[T](value, left.remove(v), right.remove(v))
  }

  /**
   * Find the leftmost node with an empty left leaf.
   */
  private def findNoLeftNode: AVLNode[T] = {
    if (left.isEmpty) this
    else left.findNoLeftNode
  }

  /**
   * Rotate a tree starting from the right node.
   * https://en.wikibooks.org/wiki/Data_Structures/Trees#/media/File:Bstreedeletenotrightchildexample.jpg
   */
  override protected def rotateNode(): AVLNode[T] = {
    val newVal = right.findNoLeftNode.value
    new AVLNode[T](newVal, left, right.remove(newVal))
  }

  override def map[V](f: (T) => V): AVLNode[V] =
    new AVLNode[V](f(value), left.map(f), right.map(f))

}

object AVLNode {
  def unapply[T](t: AVLNode[T]): Option[(T, AVLNode[T], AVLNode[T])] =
    Option((t.value, t.left, t.right))
}