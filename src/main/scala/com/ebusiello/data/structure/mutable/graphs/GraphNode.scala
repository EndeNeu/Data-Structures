package com.ebusiello.data.structure.mutable.graphs

import scala.collection.mutable
import scalaz.{ \/-, \/ }

class GraphNode[T](identifier: T) {

  val connections = mutable.Map.empty[GraphNode[T], Long]

  final def addConnection(node: GraphNode[T], cost: Long, passedNodes: List[T]): \/[GraphError, Unit] = {
    if (passedNodes.contains(identifier)) \/-(())
    else if (node.identifier == identifier) connections + ((node, cost))
    else connections.map(_._1.addConnection(node, cost, passedNodes :+ identifier))
  }

}