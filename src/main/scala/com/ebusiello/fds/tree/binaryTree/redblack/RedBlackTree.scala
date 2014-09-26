package com.ebusiello.fds.tree.binaryTree.redblack

final class RedBlackTree[T](head: AbstractRedBlackNode[T]) extends AbstractRedBlackTree[T, RedBlackTree, RedBlackTree](head) {

  override def insert(mValue: T)(implicit ord: Ordering[T]): RedBlackTree[T] =
    if (isEmpty) new RedBlackTree[T](new RedNode[T](mValue, new RedBlackEmptyNode[T](BLACK), new RedBlackEmptyNode[T](BLACK)))
    else new RedBlackTree[T](head.insert(mValue))

  override def map[V](f: (T) => V): RedBlackTree[V] =
    new RedBlackTree[V](head.map(f))

}