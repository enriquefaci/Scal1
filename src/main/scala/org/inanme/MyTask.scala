package org.inanme

import java.util.concurrent.TimeUnit

import scalaz.concurrent.{Task, _}

object MyTask extends App {

  val task: Task[Unit] = Task(println("foo"))

  task.unsafePerformSync


  val x: Task[Nothing] = Task.fail(new Exception("example"))
  val y = Task.now(1)
  val z: Task[Int] = Task.fail(new Exception("example")) or Task.now(2)


}
