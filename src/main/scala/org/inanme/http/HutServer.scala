package org.inanme.http

import java.util.UUID
import scala.collection.mutable.ListBuffer

import cats.effect.IO
import fs2.StreamApp
import io.circe.generic.auto._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext.Implicits.global

case class Hut(name: String)

case class HutWithId(id: String, name: String)

final class HutRepository(private val huts: ListBuffer[HutWithId]) {
  val makeId: IO[String] = IO {
    UUID.randomUUID().toString
  }

  def getHut(id: String): IO[Option[HutWithId]] =
    IO {
      huts.find(_.id == id)
    }

  def addHut(hut: Hut): IO[String] =
    for {
      uuid <- makeId
      _ <- IO {
        huts += hutWithId(hut, uuid)
      }
    } yield uuid

  def updateHut(hutWithId: HutWithId): IO[Unit] = {
    for {
      _ <- IO {
        huts -= hutWithId
      }
      _ <- IO {
        huts += hutWithId
      }
    } yield ()
  }

  def deleteHut(hutId: String): IO[Unit] =
    IO {
      huts.find(_.id == hutId).foreach(h => huts -= h)
    }

  def hutWithId(hut: Hut, id: String): HutWithId = HutWithId(id, hut.name)
}

object HutRepository {
  def empty = new HutRepository(ListBuffer())
}

object HutServer extends StreamApp[IO] with Http4sDsl[IO] {
  implicit val decoder = jsonOf[IO, Hut]
  implicit val decoder1 = jsonOf[IO, HutWithId]
  implicit val encoder = jsonEncoderOf[IO, HutWithId]
  val hutRepo = HutRepository.empty
  val HUTS = "huts"
  val service = HttpService[IO] {
    case GET -> Root / HUTS / hutId =>
      hutRepo.getHut(hutId)
        .flatMap(_.fold(NotFound())(Ok(_)))
    case req@POST -> Root / HUTS =>
      req.as[Hut].flatMap(hutRepo.addHut).flatMap(Created(_))
    case req@PUT -> Root / HUTS =>
      req.as[HutWithId]
        .flatMap(hutRepo.updateHut)
        .flatMap(Ok(_))
    case DELETE -> Root / HUTS / hutId =>
      hutRepo.deleteHut(hutId)
        .flatMap(_ => NoContent())
  }

  def stream(args: List[String], requestShutdown: IO[Unit]) =
    BlazeBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .mountService(service, "/")
      .serve
}

