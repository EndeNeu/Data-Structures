package com.ebusiello.data.structure.immutable.trees.binaries.redblack

import org.scalatest.{Matchers, WordSpecLike}

class RedBlackTreeSpec extends WordSpecLike with Matchers {

  trait TestContext {
    val tree = new RedBlackTree[Int]()
  }


}