package com.ebusiello.fds.tree

trait AbstractBinaryCommon[T] {

  def find(mValue: T)(implicit ord: Ordering[T]): Boolean

  def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S

  def reduceBinaryTree[S](f: (T, T) => T): T

  def isEmpty: Boolean

}

sealed abstract class AbstractBinaryTree[T] extends AbstractBinaryCommon[T] with Tree {

  def insert(mValue: T)(implicit ord: Ordering[T]): BinaryTree[T]

  def map[S](f: T => S): BinaryTree[S]

}

sealed abstract class AbstractBinaryNode[T] extends AbstractBinaryCommon[T] {

  def toTree: BinaryTree[T] = this.asInstanceOf[BinaryTree[T]]

  def toBinaryNode: BinaryNode[T] = this.asInstanceOf[BinaryNode[T]]

  def toEmptyNode: EmptyNode[T] = this.asInstanceOf[EmptyNode[T]]

  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T]

  def map[S](f: T => S): AbstractBinaryNode[S]

  // TODO balance maybe?

}

/**
 * Wrapper class that holds a reference to a tree head.
 *
 * @param head
 */
class BinaryTree[T](val head: AbstractBinaryNode[T]) extends AbstractBinaryTree[T] with SortableTree[T, BinaryTree] {


  /**
   * Tree insert (which basically is a head.insert).
   */
  def ++(mValue: T)(implicit ord: Ordering[T]): BinaryTree[T] =
    insert(mValue)

  def stringify: String =
    head.toString

  def getValue() = head match {
    case BinaryNode(v, _, _) => Some(v)
    case _ => None
  }

  /**
   * Whether a tree is empty or not depends on his head.
   */
  override def isEmpty: Boolean =
    head.isEmpty

  /**
   * An empty tree is a tree with an empty node.
   */
  def emptyTree: BinaryTree[T] =
    new BinaryTree[T](new EmptyNode[T])

  override def insert(mValue: T)(implicit ord: Ordering[T]): BinaryTree[T] =
    if (isEmpty) new BinaryTree[T](new BinaryNode[T](mValue, new EmptyNode, new EmptyNode))
    else new BinaryTree[T](head.insert(mValue))

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    if (isEmpty) false
    else head.find(mValue)

  override def map[S](f: (T) => S): BinaryTree[S] =
    if (isEmpty) new BinaryTree[S](new EmptyNode)
    else new BinaryTree[S](head.map(f))

  override def sort(implicit ord: Ordering[T]): BinaryTree[T] = {
    foldTree(List[T]())((acc, curr) => acc :+ curr)((s1, s2) => s1 ++ s2)
      //.filter(node => node.isInstanceOf[EmptyNode[T]])
      .sorted
      .foldLeft(emptyTree)((tree, value) => tree.insert(value))
  }

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    head.foldTree(z)(f)(compose)

  override def reduceBinaryTree[S](f: (T, T) => T): T = ???
}

object BinaryTree {
  def empty[T]: BinaryTree[T] = new BinaryTree[T](new EmptyNode[T])
}

class EmptyNode[T] extends AbstractBinaryNode[T] {

  override def toString =
    "E"

  override def isEmpty: Boolean =
    true

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean =
    false

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] =
    new BinaryNode[T](mValue, new EmptyNode[T], new EmptyNode[T])

  override def map[S](f: (T) => S): AbstractBinaryNode[S] =
    new EmptyNode[S]

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S =
    z

  override def reduceBinaryTree[S](f: (T, T) => T): T = ???
}

class BinaryNode[T](val value: T, val left: AbstractBinaryNode[T], val right: AbstractBinaryNode[T]) extends AbstractBinaryNode[T] {

  override def toString =
    left.toString + "--T(" + value.toString + ")--" + right.toString

  override def isEmpty: Boolean =
    false

  override def find(mValue: T)(implicit ord: Ordering[T]): Boolean = {
    if (mValue == value) true
    else {
      import ord.mkOrderingOps
      if (mValue < value) left.find(mValue)
      else right.find(mValue)
    }
  }

  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinaryNode[T] = this match {
    case BinaryNode(_, _, _) if mValue == value => this
    case BinaryNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      import ord.mkOrderingOps
      if (mValue < value) new BinaryNode(value, l.insert(mValue), r)
      else new BinaryNode(value, l, r.insert(mValue))
  }

  override def map[S](f: (T) => S): AbstractBinaryNode[S] = this match {
    case BinaryNode(_, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]) =>
      new BinaryNode[S](f(value), l.map(f), r.map(f))
  }

  override def foldTree[S](z: S)(f: (S, T) => S)(compose: (S, S) => S): S = {
    f(compose(z, compose(left.foldTree(z)(f)(compose), right.foldTree(z)(f)(compose))), value)
  }

  override def reduceBinaryTree[S](f: (T, T) => T): T = ???
}

object BinaryNode {

  def apply[T](value: T, l: AbstractBinaryNode[T], r: AbstractBinaryNode[T]): BinaryNode[T] =
    new BinaryNode[T](value, l, r)

  def unapply[T](t: BinaryNode[T]) =
    Option(t.value, t.left, t.right)

}