package com.ebusiello.fds.tree.binaryTree.random

import com.ebusiello.fds.tree.binaryTree.{BasicBinaryNode, BasicBinaryTreeWithInsert}

import scala.language.higherKinds

abstract class AbstractRandomBinaryTree[T, S[_], A[_]](head: BasicBinaryNode[T]) extends BasicBinaryTreeWithInsert[T, S, A](head) {

 }
