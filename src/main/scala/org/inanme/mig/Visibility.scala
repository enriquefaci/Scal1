package org.inanme.mig {
  object Outer extends App {

    object Inner {
      //can be referenced in Outher package
      private[Outer] def f = "This is f"
      private[this] def f1 = "This is f"
      //can be referenced in org package
      private[inanme] def g = "This is g"
      //can be referenced in org package, private in org package
      private[org] def h = "This is h"
    }

    println(Inner.f)
    //println(Inner.f1)
    println(Inner.g)
    println(Inner.h)
  }
}
package org.inanme1 {
  object Outer {
    println(org.inanme.mig.Outer.Inner.h)
  }
}

package v {
  class C1 {
    private val p = 0
    private[C1] val pClass = 1 //changing C1 to this will make this fail

    def m1(other: C1) {
      println(pClass)
      println(other.p)
      println(other.pClass)
    }
  }
}