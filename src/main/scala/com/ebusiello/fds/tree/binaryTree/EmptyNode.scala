package com.ebusiello.fds.tree.binaryTree

private[binaryTree] abstract class EmptyNode[T] extends AbstractBinaryNode[T] {

  override def leftRelativeDepth: Int =
    0

  override def rightRelativeDepth: Int =
    0

  override def toString =
    "E"

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

}