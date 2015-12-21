//package com.ebusiello.fds.tree.binaryTree.random
//
//class RandomBinaryNode[T](val value: T, val left: AbstractRandomBinaryNode[T], val right: AbstractRandomBinaryNode[T]) extends AbstractRandomBinaryNode[T] {
//
//  def randomizer = new scala.util.Random()
//
//  /**
//   * RandomBinaryTrees uses a random generator, if it returns true insert to the left,
//   * else insert to the right
//   *
//   * @note duplicate are allowed
//   */
//  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractRandomBinaryNode[T] =
//    if (randomizer.nextBoolean()) left.insert(mValue)
//    else right.insert(mValue)
//
//  /**
//   * if a tree/node is empty or not.
//   */
//  override def isEmpty: Boolean =
//    false
//
//  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
//    if (mValue == value) true
//    else left.find(mValue) || right.find(mValue)
//
//  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
//    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
//
//  override def leftRelativeDepth: Int =
//    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + left.leftRelativeDepth
//
//  override def rightRelativeDepth: Int =
//    (if (left.nonEmpty || right.nonEmpty) 1 else 0) + right.rightRelativeDepth
//
//  override def map[V](f: (T) => V): AbstractRandomBinaryNode[V] = this match {
//    case RandomBinaryNode(_, l: RandomBinaryNode[T], r: RandomBinaryNode[T]) =>
//      new RandomBinaryNode[V](f(value), l.map(f), r.map(f))
//  }
//}
//
//private[binaryTree] object RandomBinaryNode {
//  def unapply[T](t: RandomBinaryNode[T]) =
//    Option(t.value, t.left, t.right)
//}
//
