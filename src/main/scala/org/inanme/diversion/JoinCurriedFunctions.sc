trait Cat
trait Bird
trait Catch
trait FullTummy

val catch1: Cat => Bird => Cat with Bird = _ => _ => new Cat with Bird
def catch2(cat: Cat, bird: Bird): Cat with Bird = new Cat with Bird
val eat: Cat with Bird => Cat with FullTummy = _ => new Cat with FullTummy

val meal1: Cat => Bird => Cat with FullTummy = catch1(_) andThen eat
val meal2: Cat => Bird => Cat with FullTummy = (catch2 _).curried(_) andThen eat

println(meal1(new Cat {})(new Bird {}))
println(meal2(new Cat {})(new Bird {}))
