package org.inanme

import scalaz.concurrent.Task

object MyTask extends App {
  val task: Task[Unit] = Task(println("foo"))
  task.unsafePerformSync
  val x: Task[Nothing] = Task.fail(new Exception("example"))
  val y = Task.now(1)
  val z: Task[Int] = Task.fail(new Exception("example")) or Task.now(2)

}
