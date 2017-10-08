package org.inanme.mig

import scala.util.Random

object Focomo2 extends App {

  class Order

  trait MaybeOrder {
    def map(f: Order => Order): MaybeOrder

    def flatMap(f: Order => MaybeOrder): MaybeOrder
  }

  case class GoodOrder(order: Order) extends MaybeOrder {
    def map(f: Order => Order): MaybeOrder = {
      GoodOrder(f(order))
    }

    def flatMap(f: Order => MaybeOrder): MaybeOrder = {
      f(order)
    }
  }

  case class BadOrder(reason: String) extends MaybeOrder {
    def map(process: Order => Order) = {
      println("bad map")
      BadOrder(reason)
    }

    def flatMap(process: Order => MaybeOrder) = {
      println("bad flatMap")
      BadOrder(reason)
    }

    def getReason = reason
  }

  val goodOrder = GoodOrder(new Order)
  val badOrder = BadOrder("none")

  def getOrder = if (Random.nextBoolean) goodOrder else badOrder

  def creditCheck(order: Order): MaybeOrder = {
    println("creditCheck")
    GoodOrder(order)
  }

  def stockCheck(order: Order): MaybeOrder = {
    println("stockCheck")
    GoodOrder(order)
  }

  def process(order: Order): MaybeOrder = {
    println("process")
    GoodOrder(order)
  }

  def save(order: Order): MaybeOrder = {
    println("save")
    GoodOrder(order)
  }

  //goodOrder ~> { creditCheck } ~> { stockCheck } -> process -> save
  //badOrder ~> { creditCheck } ~> { stockCheck } -> process -> save

  val order = for {
    o1 <- getOrder
    //o1 <- badOrder //compiler infers result
    o2 <- creditCheck(o1)
    o3 <- stockCheck(o2)
    o4 <- save(o3)
    o5 <- process(o4)
  } yield o5

  order match {
    case GoodOrder(order) => println(order)
    case b: BadOrder => println(b.getReason)
  }

}
