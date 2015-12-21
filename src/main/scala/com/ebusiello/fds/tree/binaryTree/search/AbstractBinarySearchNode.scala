//package com.ebusiello.fds.tree.binaryTree.search
//
//import com.ebusiello.fds.tree.binaryTree.GenericBinaryNode
//import com.ebusiello.fds.tree.binaryTree.balanced.AbstractBalancedBinaryNode
//import com.ebusiello.fds.com.ebusiello.fds.tree.generic.com.ebusiello.fds.tree.DeletableTree
//
///**
// * This class is needed to have a common super type with a map method inside for both binary search nodes
// * and binary empty search nodes.
// */
//private[binaryTree] abstract class AbstractBinarySearchNode[T] extends GenericBinaryNode[T] with DeletableTree[T, AbstractBinarySearchNode] {
//  def map[V](f: T => V): AbstractBalancedBinaryNode[V]
//
//  def insert(mValue: T)(implicit ord: Ordering[T]): AbstractBinarySearchNode[T]
//
//}
