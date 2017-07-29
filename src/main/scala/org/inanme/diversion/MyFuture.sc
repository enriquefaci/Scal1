import scala.concurrent.{ExecutionContext, _}

implicit val currThreadExecutor =
  ExecutionContext.fromExecutor((command: Runnable) => command.run())

val hostnames = List("alpha.example.com","beta.example.com", "gamma.demo.com")

def getUptime(hostname: String): Future[Int] =
  Future(hostname.length * 60) // just for demonstration

val allUptimes: Future[List[Int]] =
  Future.traverse(hostnames)(getUptime)
