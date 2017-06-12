import scalaz.effect.IO._
import scalaz.effect._

def program: IO[Unit] = for {
  line <- readLn
  _    <- putStrLn(line)
} yield ()

val action = for {
  _ <- putStrLn("Hello, world!")
} yield ()

//program.unsafePerformIO()
action.unsafePerformIO()
