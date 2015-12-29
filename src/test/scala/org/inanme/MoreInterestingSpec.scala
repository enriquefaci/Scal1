package org.inanme

import org.scalatest.FeatureSpec

class MoreInterestingSpec extends FeatureSpec {

  feature("An element can be added to an empty mutable Set") {
    scenario("When an element is added to an empty mutable Set") {

      info("info is recorded")
      markup("markup is *also* recorded")
      note("notes are sent immediately")
      alert("alerts are also sent immediately")

    }
  }
}
