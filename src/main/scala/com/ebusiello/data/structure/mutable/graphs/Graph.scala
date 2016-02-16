package com.ebusiello.data.structure.mutable.graphs

class Graph[T](head: GraphNode[T]) {

  def addConnection(node: GraphNode[T], cost: Long): Unit = {
    head.addConnection(node, cost, List())

  }

}
