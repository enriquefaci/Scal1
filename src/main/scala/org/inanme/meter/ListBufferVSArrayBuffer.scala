package org.inanme.meter

import org.scalameter.api._
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object ListBufferVSArrayBuffer extends Bench.ForkedTime {
  val range = Gen.range("int")(1, 10, 1)
  val single = Gen.unit("mert")
  val myrange = 1 to 1000
  performance of "ListBuffer" in {
    measure method "append" in {
      using(single) in { _ ⇒
        val l = new ListBuffer[Int]()
        myrange.foreach(it ⇒ l.append(it))
      }
    }
    measure method "prepend" in {
      using(single) in { _ ⇒
        val l = new ListBuffer[Int]()
        myrange.foreach(it ⇒ l.prepend(it))
      }
    }
  }
  performance of "ArrayBuffer" in {
    measure method "append" in {
      using(single) in { _ ⇒
        val l = new ArrayBuffer[Int]()
        myrange.foreach(it ⇒ l.append(it))
      }
    }
    measure method "prepend" in {
      using(single) in { _ ⇒
        val l = new ArrayBuffer[Int]()
        myrange.foreach(it ⇒ l.prepend(it))
      }
    }
  }
  performance of "ArrayBuffer(10000)" in {
    measure method "append" in {
      using(single) in { _ ⇒
        val l = new ArrayBuffer[Int](10000)
        myrange.foreach(it ⇒ l.append(it))
      }
    }
    measure method "prepend" in {
      using(single) in { _ ⇒
        val l = new ArrayBuffer[Int](10000)
        myrange.foreach(it ⇒ l.prepend(it))
      }
    }
  }
}
