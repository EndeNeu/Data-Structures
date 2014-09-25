package com.ebusiello.fds.tree.binaryTree.redblack

final class BlackNode[T](value: T, left: AbstractRedBlackNode[T], right: AbstractRedBlackNode[T]) extends RedBlackNode[T](value, BLACK, left, right) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRedBlackNode[T] = this match {
    case BlackNode(v, l: RedBlackEmptyNode[T], r: RedBlackEmptyNode[T]) => new BlackNode[T](v, l.insert(mValue), r)
    case BlackNode(v, l: RedNode[T], r: RedNode[T]) =>
      if (l.leftRelativeDepth <= r.rightRelativeDepth) new BlackNode[T](v, l.insert(mValue), r)
      else new BlackNode[T](v, l, r.insert(mValue))
    case BlackNode(v, l: RedNode[T], r: RedBlackEmptyNode[T]) => new BlackNode[T](v, l, r.insert(mValue))
  }

  override def map[V](f: (T) => V): AbstractRedBlackNode[V] =
    new BlackNode[V](f(value), left.map(f), right.map(f))
}


object BlackNode {

  def unapply[T](r: BlackNode[T]) = Some(r.value, r.left, r.right)

}