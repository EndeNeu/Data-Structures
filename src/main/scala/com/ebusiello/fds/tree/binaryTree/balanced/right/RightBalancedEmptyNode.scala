//package com.ebusiello.fds.tree.binaryTree.balanced.right
//
//import com.ebusiello.fds.tree.binaryTree.balanced.EmptyBalancedBinaryNode
//
//class RightBalancedEmptyNode[T] extends EmptyBalancedBinaryNode[T] {
//
//  override def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBalancedBinaryNode[T] =
//    new RightGenericBalancedBinaryNode[T](mValue, new RightBalancedEmptyNode[T], new RightBalancedEmptyNode[T])
//
//  override def map[V, VLR <: AbstractBalancedBinaryNode[V]](f: (T) => V): AbstractBalancedBinaryNode[V] =
//    new RightBalancedEmptyNode[V]
//
//  override def length: Int = 0
//}