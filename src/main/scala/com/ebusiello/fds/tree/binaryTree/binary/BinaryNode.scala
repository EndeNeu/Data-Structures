package com.ebusiello.fds.tree.binaryTree.binary

class BinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  /**
   * Insert can happen between leafs, the where value specifies where the insert should happen, if no value
   * that is equal to where is found, the value is not inserted, note that there could be many M =< N inserts
   * where N is the number of occurrence of where in the tree:
   *
   *
   *    1 insert(4) where(2) -->  1
   *   / \                       / \
   *  2  3                      4  3
   *    / \                    /  / \
   *   2  E                   2  4  E
   *                            / \
   *                           2  E
   */
  override def insert(mValue: T, where: T): AbstractBinaryNode[T] =
    if (where == value) new BinaryNode[T](mValue, new BinaryNode[T](value, left, new EmptyBinaryNode[T]), right)
    else new BinaryNode[T](mValue, left.insert(mValue, where), right.insert(mValue, where))

  override def map[V](f: (T) => V): AbstractBinaryNode[V] =
    new BinaryNode[V](f(value), left.map(f), right.map(f))

  override def isEmpty: Boolean =
    false

  /**
   * Note that finding a value in a balanced tree can lead to the full tree traversal, that is
   * if the value is found the function will keep traversing the tree looking for the value until reaches
   * an empty node.
   */
  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (value == mValue) true
    else left.find(mValue) || right.find(mValue)

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)

  override def leftRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + left.leftRelativeDepth

  override def rightRelativeDepth: Int =
    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + right.rightRelativeDepth
}

private[binaryTree] object BinaryNode {
  def unapply[T](t: BinaryNode[T]) =
    Option(t.value, t.left, t.right)
}
