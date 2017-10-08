package org.inanme

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

import scala.concurrent.ExecutionContextExecutor

//https://gist.github.com/rklaehn/3aa3215046df2c0a7795

trait H8349347348923 {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
}
object S842048023 extends App with H8349347348923 {
  val route =
    path("metrics") {
      post {
        headerValueByName("name") { headerValue ⇒
          complete(s"$headerValue $headerValue")
        }
      }
    }
  Http(system).bindAndHandle(route, "localhost", 8080)
}
object P8409238420 extends App with H8349347348923 {
  val proxy = Route { context =>
    val request = context.request
    println("Opening connection to " + request.uri.authority.host.address)
    //val flow = Http(system).outgoingConnection(request.uri.authority.host.address(), 8080)
    val flow = Http(system).outgoingConnection("localhost", 8080)
    val handler = Source.single(context.request)
      .via(flow)
      .runWith(Sink.head)
      .flatMap(context.complete(_))
    handler
  }
  val binding = Http(system).bindAndHandle(handler = proxy, interface = "localhost", port = 8081)

}
object SX748321323 extends App with H8349347348923 {
  val proxy = Route { context =>
    val request = context.request
    println("Opening connection to " + request.uri.authority.host.address)
    //val flow = Http(system).outgoingConnection(request.uri.authority.host.address(), 8080)
    val flow = Http(system).outgoingConnection("localhost", 8080)
    val handler = Source.single(context.request)
      .via(flow)
      .runWith(Sink.head)
      .flatMap(context.complete(_))
    handler
  }
  val route =
    path("no-proxy") {
      post {
        //extractHostPort { name ⇒
        complete("real server")
        //}
      }
    } ~ proxy
  Http(system).bindAndHandle(route, "localhost", 8081)

}
