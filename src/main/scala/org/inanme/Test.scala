package org.inanme

case class MyBox[T](value: T) {
  def foreach[U](f: T => U): Unit = {
    f(value)
  }

  def map[R](f: T => R): MyBox[R] = {
    println("begin map")
    val res = new MyBox(f(value))
    println("end map")
    res
  }

  def flatMap[R](f: T => MyBox[R]): MyBox[R] = {
    println("begin fmap")
    val res = f(value)
    println("end fmap")
    res
  }
}

object Test extends App {

  val ma = new MyBox("hello")

  //  val res = for {
  //    a <- ma
  //  } yield a.length + 2
  //  println(res)

  val mb = new MyBox("mert")


  //val res1 = ma.flatMap(a => mb.map(b => b.size + a.size))
  val res2 = for {
    a <- ma
    b <- mb
  } yield a.length + b.length

 // println(res1)
 println(res2)

 // for (a <- ma) println(a)
 // ma.foreach(println)
}
