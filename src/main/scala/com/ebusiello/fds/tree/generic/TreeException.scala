package com.ebusiello.fds.tree.generic

object TreeException {
  def create(msg: String): TreeException = new TreeException(msg)

  def create(msg: String, cause: Throwable) = new TreeException(msg).initCause(cause)
}

class TreeException(msg: String) extends RuntimeException(msg)

object TreeMutationException {
  def create(msg: String): TreeException = new TreeException(msg)

  def create(msg: String, cause: Throwable) = new TreeException(msg).initCause(cause)
}

class TreeMutationException(msg: String) extends RuntimeException(msg)