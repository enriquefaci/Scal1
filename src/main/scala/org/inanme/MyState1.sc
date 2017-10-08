//http://timperrett.com/2013/11/25/understanding-state-monad/

import scalaz._, Scalaz._

sealed trait Aspect

case object Green extends Aspect

case object Amber extends Aspect

case object Red extends Aspect

sealed trait Mode

case object Off extends Mode

case object Flashing extends Mode

case object Solid extends Mode

// represents the actual display set: must be enabled before 
// it can be used. 
case class Signal(isOperational: Boolean,
                  display: Map[Aspect, Mode])

object Signal {
  // just a lil' bit of sugar to use later on.
  type ->[A, B] = (A, B)

  // convenience alias as all state ops here will deal
  // with signal state.
  type SignalState[A] = State[Signal, A]

  // dysfunctional lights revert to their flashing 
  // red lights to act as a stop sign to keep folks safe.
  val default = Signal(
    isOperational = false,
    display = Map(Red -> Flashing, Amber -> Off, Green -> Off))
}

def enable: State[Signal, Boolean] =
  for {
    _ ← modify((s: Signal) ⇒ s.copy(isOperational = true))
    r ← get
  } yield r.isOperational

val (signal, ops) = enable(Signal.default)

def setMode(aspect: Aspect): State[Signal, Unit] =
  for {
    _ ← modify((s: Signal) ⇒ s.copy(display = (s.display - aspect).mapValues(_ ⇒ Off) + (aspect → Solid)))
  } yield ()

val more: State[Signal, Unit] =
  for {
    _ ← enable
    _ ← setMode(Green)
  } yield ()

more(Signal.default)
