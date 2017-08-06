class X[-A]
class Y[+A]
class Z[A]

//contravariant
val x: X[String] = new X[Object] //Specialize -, should not be exposed
//covariant
val y: Y[Object] = new Y[String] //Generalize +, can be exposed
//invariant
val z: Z[String] = new Z[String]

class Emp
class Manager extends Emp
class CEO extends Manager
class Award[+T](val recipient: T) //Generalize + up
class Problem[-T](recipient: T) //Specialize - down
class Action1[+T <: Manager](val recipient: T) //Specialize < down
class Action2[-T >: Manager](recipient: T) //Generalize > up

// Function[-A, +B]
val f1: Function[Manager, Manager] = _ => new CEO
val f2: Function[CEO, Emp] = f1

val m = new Manager
val a = new Award[Manager](m)
val ea: Award[Emp] = a
//val ca: Award[CEO] = a

val t = new Problem[Emp](m)
val ep: Problem[Manager] = t

val action1: Action1[Manager] = new Action1[CEO](new CEO)
val action2: Action2[Manager] = new Action2[Emp](new Emp)