import scala.concurrent._
import scalaz.EitherT._
import scalaz.Scalaz._
import scalaz._

implicit val currThreadExecutor = ExecutionContext.fromExecutor((command: Runnable) => command.run())

type Parameters = Map[String, String]
type RequestHeader = Parameters
type ErrorResponse = String

case class Client(name: String)

// retrieves the request paramters from the given request
def requestParameters(request: RequestHeader): Either[ErrorResponse, Parameters] = Right(request)

// retrieves the response type from the given parameters
def responseType(parameters: Parameters): Either[ErrorResponse, String] = Right("done")

// retrieves the client id from the given parameters
def clientId(p: Parameters): Either[ErrorResponse, String] = Right("Client id")

// retrieves the client using the given client id
def client(clientId: String): Future[Either[ErrorResponse, Client]] = Future(Right(Client("mert")))

// validates the response type of the client
def validateResponseType(client: Client, responseType: String): Option[ErrorResponse] = None

implicit val futureMonad = new Monad[Future] {
  override def point[A](a: ⇒ A): Future[A] = Future.successful(a)

  override def bind[A, B](fa: Future[A])(f: A ⇒ Future[B]): Future[B] = fa.flatMap(f)
}

val result1: RequestHeader ⇒ EitherT[Future, ErrorResponse, Client] = request =>
  for {
    parameters <- fromEither(Future(requestParameters(request)))
    clientId <- fromEither(Future(clientId(parameters)))
    responseType <- fromEither(Future(responseType(parameters)))
    client <- fromEither(client(clientId))
    response <- fromEither(Future(validateResponseType(client, responseType).toLeft(client)))
  } yield response

val run1: Future[\/[ErrorResponse, Client]] = result1(Map()).run

def fromFuture[L, R](x: Either[L, R]): EitherT[Future, L, R] = fromEither(Future(x))

val result2: RequestHeader ⇒ EitherT[Future, ErrorResponse, Client] = request =>
  for {
    parameters <- requestParameters(request)                              |> fromFuture
    clientId <- clientId(parameters)                                      |> fromFuture
    responseType <- responseType(parameters)                              |> fromFuture
    client <- fromEither(client(clientId))
    response <- validateResponseType(client, responseType).toLeft(client) |> fromFuture
  } yield response

val run2: Future[\/[ErrorResponse, Client]] = result2(Map()).run
