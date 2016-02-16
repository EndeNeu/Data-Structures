package com.ebusiello.data.structure.mutable.graphs

trait GraphError { def message: String }

class NodeNotFound(val message: String) extends GraphError
