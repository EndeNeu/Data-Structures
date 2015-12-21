//package com.ebusiello.fds.tree.binaryTree.redblack
//
//sealed abstract class Color(val isBlack: Boolean) {
//  def reverse: Color
//}
//
//case object RED extends Color(false) {
//  def reverse = BLACK
//}
//
//case object BLACK extends Color(true) {
//  def reverse = RED
//}