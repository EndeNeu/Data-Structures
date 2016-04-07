package com.ebusiello.data.structure.immutable.trees.binaries.redblack

import com.ebusiello.data.structure.immutable.trees.binaries.{GenericBinaryNode, GenericBinaryTree}
import com.ebusiello.data.structure.immutable.trees.generic.node._
import com.ebusiello.data.structure.immutable.trees.generic.tree.BalanceableTree

/**
  * RedBlack invariants:
  *
  * - no red node has a red child
  * - every path from the root to an empty node contains the same number of black nodes.
  */
final class RedBlackTree[T] private(val head: RedBlackNode[T]) extends GenericBinaryTree[T, RedBlackTree, RedBlackNode] with BalanceableTree[T, RedBlackTree] {

  def this() = this(new EmptyRedBlackNode[T])

  override def remove(v: T)(implicit ord: Ordering[T]): RedBlackTree[T] = ???

  override def insert(value: T)(implicit ord: Ordering[T]): RedBlackTree[T] =
  // if this is the first node, insert it directly black without resorting
    if (head.isEmpty) new RedBlackTree[T](new RedBlackNode[T](value, new EmptyRedBlackNode[T], new EmptyRedBlackNode[T], BLACK))
    // else insert a red node and rebalance
    else new RedBlackTree[T](head.insert(value)).resort()

  override def map[V](f: (T) => V)(implicit ord: Ordering[T], ord2: Ordering[V]): RedBlackTree[V] = ???

  /**
    * Resort the tree to respect the invariants.
    */
  override def resort()(implicit ord: Ordering[T]): RedBlackTree[T] = {
    // first invariant, the root node must be black
    val resorted = head.resort()
    if(resorted.color.isRed) new RedBlackTree[T](resorted.recolor())
    else new RedBlackTree[T](resorted)
  }
}

private[redblack] class RedBlackNode[T](val value: T, val left: RedBlackNode[T], val right: RedBlackNode[T], val color: Color) extends GenericBinaryNode[T, RedBlackNode] with ColoredNode with OrderableNode[T, RedBlackNode] with BalanceableNode[T, RedBlackNode] {

  override def map[V](f: (T) => V): RedBlackNode[V] = ???

  override def remove(v: T): RedBlackNode[T] = ???

  override def insert(newValue: T)(implicit ord: Ordering[T]): RedBlackNode[T] =
    if (value == newValue) this
    else if (ord.lt(newValue, value)) new RedBlackNode[T](value, left.insert(newValue), right, left.color)
    else new RedBlackNode[T](value, left, right.insert(newValue), right.color)

  override def resort()(implicit ord: Ordering[T]): RedBlackNode[T] = this match {
    // Second invariant, a red node cannot have a red son.
    // case 3, left branch zig-zig
    case RedBlackNode(v, RedBlackNode(lv, ll, lr, lcolor), r: EmptyRedBlackNode[T], BLACK)
      if ll.nonEmpty && lcolor.isRed && ll.color.isRed =>
        new RedBlackNode[T](v, new RedBlackNode[T](lv, ll, lr, BLACK), r, RED).resort()
    case RedBlackNode(v, RedBlackNode(lv, ll, lr, lcolor), RedBlackNode(rv, rl, rr, rcolor), BLACK)
      if ll.nonEmpty && lcolor.isRed && ll.color.isRed =>
        new RedBlackNode[T](v, new RedBlackNode[T](lv, ll, lr, BLACK), new RedBlackNode[T](rv, rl, rr, BLACK), RED).resort()

    // case 3, left branch zig-zag
    case RedBlackNode(v, RedBlackNode(lv, ll, lr, lcolor), r: EmptyRedBlackNode[T], BLACK) if lr.nonEmpty && lcolor.isRed && lr.color.isRed =>
      new RedBlackNode[T](v, new RedBlackNode[T](lv, ll, lr, BLACK), r, RED).resort()
    case RedBlackNode(v, RedBlackNode(lv, ll, lr, lcolor), RedBlackNode(rv, rl, rr, rcolor), BLACK) if lr.nonEmpty && lcolor.isRed && lr.color.isRed =>
      new RedBlackNode[T](v, new RedBlackNode[T](lv, ll, lr, BLACK), new RedBlackNode[T](rv, rl, rr, BLACK), RED).resort()

    // case 3, right branch zig-zig

    // case 3, right branch zig-zag


    // default case, nothing to do, recursively apply resort
    case RedBlackNode(v, l, r, c) =>
      new RedBlackNode[T](v, l.resort(), r.resort(), c)
  }

  def recolor() =
   if(isBlack) new RedBlackNode[T](value, left, right, RED)
   else new RedBlackNode[T](value, left, right, BLACK)
}

object RedBlackNode {
  def unapply[T](n: RedBlackNode[T]) = Some((n.value, n.left, n.right, n.color))
}


private[redblack] class EmptyRedBlackNode[T]() extends RedBlackNode[T](null.asInstanceOf[T], null, null, BLACK) {
  override def isEmpty: Boolean =
    true

  override def insert(newValue: T)(implicit ord: Ordering[T]): RedBlackNode[T] =
    new RedBlackNode[T](newValue, new EmptyRedBlackNode[T], new EmptyRedBlackNode[T], RED)

  override def resort()(implicit ord: Ordering[T]): RedBlackNode[T] =
    this
}

object EmptyRedBlackNode {
  def unapply[T](e: EmptyRedBlackNode[T]) = Some(e.color)
}
