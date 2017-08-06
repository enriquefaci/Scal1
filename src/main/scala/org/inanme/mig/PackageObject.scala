package org.inanme

package object mig {
  val minimumAge = 18
  def verifyAge(age: Int): Boolean = age > minimumAge
}
package mig {
  object BarTender extends App {
    println(verifyAge(23))
  }
}
