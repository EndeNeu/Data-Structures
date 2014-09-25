package com.ebusiello.fds.tree.binaryTree.redblack

import scala.language.higherKinds
import com.ebusiello.fds.tree.binaryTree.{BasicBinaryNode, BasicBinaryTreeWithInsert}

abstract class AbstractRedBlackTree[T, S[_], A[_]](head: BasicBinaryNode[T]) extends BasicBinaryTreeWithInsert[T, S, A](head) {

}
