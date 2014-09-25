package com.ebusiello.fds.tree.binaryTree.redblack

final class RedNode[T](value: T, left: AbstractRedBlackNode[T], right: AbstractRedBlackNode[T]) extends RedBlackNode[T](value, RED, left, right) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRedBlackNode[T] = this match {
    case RedNode(v, l: RedBlackEmptyNode[T], r: RedBlackEmptyNode[T]) => new RedNode[T](v, l.insert(mValue), r)
    case RedNode(v, l: BlackNode[T], r: BlackNode[T]) =>
      if (l.leftRelativeDepth <= r.rightRelativeDepth) new RedNode[T](v, l.insert(mValue), r)
      else new RedNode[T](v, l, r.insert(mValue))
    case RedNode(v, l: BlackNode[T], r: RedBlackEmptyNode[T]) => new RedNode[T](v, l, r.insert(mValue))
  }

  override def map[V](f: (T) => V): AbstractRedBlackNode[V] =
    new RedNode[V](f(value), left.map(f), right.map(f))
}

object RedNode {

  def unapply[T](r: RedNode[T]) = Some(r.value, r.left, r.right)

}