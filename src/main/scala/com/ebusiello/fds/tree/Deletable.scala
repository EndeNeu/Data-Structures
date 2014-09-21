package com.ebusiello.fds.tree

import scala.language.higherKinds

trait Deletable[T, S[_]] {

  def delete(mValue: T)(implicit ord: Ordering[T]): S[T]

}
