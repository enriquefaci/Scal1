package org.inanme.mig

//https://coderwall.com/p/t_rapw
trait FooAble {
  def foo = "here is your foo"
}

trait FooAble1 {
  def foo1 = "here is your foo1"
}

class BarUsingFooAble {
  this: FooAble with FooAble1 => //see note #1
  def bar = "bar calls foo: " + foo //see note #2
  def bar1 = "bar calls foo: " + foo //see note #2
}

object CakeMain extends App {
  val barWithFoo = new BarUsingFooAble with FooAble with FooAble1 //see note #3
  println(barWithFoo.bar1)
}