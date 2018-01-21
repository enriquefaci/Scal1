//def a / val a ==> type x
//x:t           ==> x <: T
//def foo(x:T)  ==> type[x<:T]
//the return type must be omitted
//this          ==> this.type
//x.foo         ==> x#foo

// https://www.youtube.com/watch?v=vOyLwT2lXsw
// https://www.youtube.com/watch?v=_-J4YRI1rAw
sealed trait BooleanType {
  type not <: BooleanType
  type or[that <: BooleanType] <: BooleanType
  type and[that <: BooleanType] =
    this.type#not#or[that#not]#not
}
sealed trait False extends BooleanType {
  type not = True
  type or[that <: BooleanType] = that
}
sealed trait True extends BooleanType {
  type not = True
  type or[that <: BooleanType] = True
}
val m: False#not = null
implicitly[True =:= True]
