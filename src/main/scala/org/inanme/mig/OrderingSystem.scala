package org.inanme.mig

trait OrderingSystem {
  type O <: Order
  type I <: Inventory
  type S <: Shipping

  trait Order {
    def placeOrder(i: I): Unit
  }
  trait Inventory {
    def itemExists(order: O): Boolean
  }
  trait Shipping {
    def scheduleShipping(order: O): Long
  }

  //trait Ordering extends Inventory with Shipping
  trait Ordering {
    this: I with S =>
    def placeOrder(o: O): Option[Long] = {
      if (itemExists(o)) {
        o.placeOrder(this)
        Some(scheduleShipping(o))
      } else None
    }
  }
}

object BookOrderingSystem extends OrderingSystem {
  class BookOrder extends Order {
    def placeOrder(i: AmazonBookStore): Unit = ???
  }
  trait AmazonBookStore extends Inventory {
    def itemExists(o: BookOrder): Boolean = ???
  }
  trait UPS extends Shipping {
    def scheduleShipping(order: BookOrder): Long = ???
  }

  type O = BookOrder
  type I = AmazonBookStore
  type S = UPS

  object BookOrdering extends Ordering with AmazonBookStore with UPS

}

object Currying1 extends App {
  BookOrderingSystem.BookOrdering
}