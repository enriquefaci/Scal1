package org.inanme

import org.scalatest._

class LifecycleSpec extends FlatSpec {
  var k = 1
  it should "print k" in {
    println(k)
    k = k + 1
  }
  it should "print k again" in {
    println(k)
    k = k + 1
  }

}
