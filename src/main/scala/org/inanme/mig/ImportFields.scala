package org.inanme.mig

object ImportFields extends App {

  class Address1(no: Int, street: String, city: String, state: String, zip: String)
  case class Address(no: Int, street: String, city: String, state: String, zip: String)

  trait LabelMaker[T] {
    def toLabel(value: T): String
  }

  object AddressLabelMaker extends LabelMaker[Address] {
    def toLabel(address: Address) = {
      import address._
      "%d %s, %s, %s - %s".format(no, street, city, state, zip)
    }
  }

  val addr = AddressLabelMaker.toLabel(Address(100, "Monroe Street", "Denver", "CO", "80231"))

  println(addr)
}