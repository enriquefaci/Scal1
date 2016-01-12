package org.inanme

import org.scalatest.FlatSpec

class SunSpec extends FlatSpec {

  behavior of "Sun"

  it should "have size 0" in {
    val ordered = (a: Int, b: Int) => (a < b)
    assert(Sun.isSorted(Array(), ordered))
    assert(Sun.isSorted(Array(1), ordered))
    assert(Sun.isSorted(Array(1, 2), ordered))
    assert(Sun.isSorted(Array(1, 2, 3), ordered))
    assert(!Sun.isSorted(Array(1, 2, 1), ordered))
    assert(!Sun.isSorted(Array(1, 2, 1, 2), ordered))
    assert(!Sun.isSorted(Array(1, 2, 1, 2, 1), ordered))
    assert(!Sun.isSorted(Array(1, 2, 1, 2, 1, 3), ordered))
  }

}
