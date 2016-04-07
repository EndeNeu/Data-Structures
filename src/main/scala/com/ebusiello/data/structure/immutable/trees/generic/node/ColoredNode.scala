package com.ebusiello.data.structure.immutable.trees.generic.node

trait ColoredNode {
  val color: Color
  def isBlack: Boolean = color == BLACK
  def isRed: Boolean = !isBlack
}

sealed abstract class Color(val black: Boolean) {
  def reverse: Color
  def isBlack = black
  def isRed = !isBlack
}

case object BLACK extends Color(true) {
  def reverse = RED
}

case object RED extends Color(false) {
  def reverse = BLACK
}