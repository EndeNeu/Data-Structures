package stack

import com.ebusiello.fds.stack.Stack
import org.specs2.mutable.Specification

class StackSpec extends Specification {

  val emptyStack = Stack.empty[Int]
  val nonEmptyStack = new Stack[Int](List(1, 4, 3, 7, 5, 6, 7))


  "Stack" should {
    "be empy" in {
      emptyStack.isEmpty must beEqualTo(true)
      emptyStack.push(3).isEmpty must beEqualTo(false)
      nonEmptyStack.isEmpty must beEqualTo(false)
      nonEmptyStack.pop.pop.pop.pop.pop.pop.pop.isEmpty must beEqualTo(true)
    }
    "insert" in {
      emptyStack.push(1).top must beEqualTo(1)
      nonEmptyStack.push(1).top must beEqualTo(1)
      nonEmptyStack.top must beEqualTo(7)
    }
    "pop" in {

      nonEmptyStack.pop.top must beEqualTo(6)
      nonEmptyStack.pop.pop.top must beEqualTo(5)

    }
  }

}
