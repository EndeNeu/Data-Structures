package com.ebusiello.fds.tree.binaryTree.search

import com.ebusiello.fds.tree.binaryTree.BasicBinaryTreeWithInsert
import com.ebusiello.fds.tree.binaryTree.balanced.LeftBalancedBinaryTree

/**
 * This class is needed to have a common super type with a map method inside for both binary search nodes
 * and binary empty search nodes.
 */
private[binaryTree] abstract class AbstractBinarySearchTree[T](head: AbstractBinarySearchNode[T]) extends BasicBinaryTreeWithInsert[T, BinarySearchTree, LeftBalancedBinaryTree](head) {
}