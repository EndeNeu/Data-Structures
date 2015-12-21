//package com.ebusiello.fds.tree.binaryTree.balanced.left
//
//import com.ebusiello.fds.tree.binaryTree.GenericBinaryNode
//import com.ebusiello.fds.tree.binaryTree.balanced.generic.GenericBalancedBinaryNode
//
///**
// * Concrete implementation of a left balanced empty node.
// */
//class LeftBalancedEmptyNode[T] extends GenericBalancedBinaryNode[T] {
//
//  override def insert(mValue: T)(implicit ord: Ordering[T]): GenericBinaryNode[T] =
//    new LeftBalancedBinaryNode[T](mValue, new LeftBalancedEmptyNode[T], new LeftBalancedEmptyNode[T])
//
//  override def map[V, VLR <: AbstractBalancedBinaryNode[V]](f: (T) => V): AbstractBalancedBinaryNode[V] =
//    new LeftBalancedEmptyNode[V]
//
//  override def length: Int = 0
//}
