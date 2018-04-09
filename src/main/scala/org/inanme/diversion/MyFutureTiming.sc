import cats._
import scala.concurrent.duration.Duration
import scala.util._
import scala.concurrent.{ExecutionContext, _}

//implicit val currThreadExecutor: ExecutionContextExecutor =
//  ExecutionContext.fromExecutor((command: Runnable) => command.run())

import scala.concurrent.ExecutionContext.Implicits.global

val m = Eval.later {
  Future {
    println("in future")
    Thread.sleep(200L)
    1
  }
}

def timing1[A](ef: Eval[Future[A]]): Eval[Future[A]] = {
  println("start1")
  val m = System.currentTimeMillis()
  ef.map { f ⇒
    f.onComplete {
      case Success(_) ⇒ println(s"done1 - ${System.currentTimeMillis() - m}")
      case Failure(_) ⇒ println("failure1")
    }
    f
  }
}

def timing2[A](ef: Eval[Future[A]]): Eval[Future[A]] = {
  println("start2")
  val m = System.currentTimeMillis()
  ef.map { f ⇒
    f.onComplete {
      case Success(_) ⇒ println(s"done2 - ${System.currentTimeMillis() - m}")
      case Failure(_) ⇒ println("failure2")
    }
    f
  }
}

def timingx[A](k: Eval[Future[A]]) = timing2(timing1(k))

val timing = timing2[Int] _ compose timing1[Int]

Await.result(timing(m).value, Duration.Inf)

