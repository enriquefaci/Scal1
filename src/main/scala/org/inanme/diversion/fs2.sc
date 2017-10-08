import java.nio.file.Paths

import cats.effect.{IO, Sync}
import fs2.{io, text}

def fahrenheitToCelsius(f: Double): Double =
  (f - 32.0) * (5.0/9.0)

def converter[F[_]](implicit F: Sync[F]): F[Unit] =
  io.file.readAll[F](Paths.get("testdata/fahrenheit.txt"), 4096)
    .through(text.utf8Decode)
    .through(text.lines)
    .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
    .map(line => fahrenheitToCelsius(line.toDouble).toString)
    .intersperse("\n")
    .through(text.utf8Encode)
    .through(io.file.writeAll(Paths.get("testdata/celsius.txt")))
    .run

// at the end of the universe...
val u: Unit = converter[IO].unsafeRunSync()
