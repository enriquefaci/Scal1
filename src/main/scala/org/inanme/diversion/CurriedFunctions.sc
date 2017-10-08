
def sum1(a: Int, b: Int, c: Int) = a + b + c
def sum2(a: Int)(b: Int)(c: Int) = a + b + c


val m0 = sum1(1, _: Int, 3)
val m1 = (sum1 _).curried
val m2 = sum2(1)(2) _
val m3 = sum2(1) _
val m4 = (sum1 _).tupled

m0(2)
m1(1)(2)(3)
m2(3)
m3(2)(3)
m4((1, 2, 3)) == m4(1, 2, 3)


def b1 = {
  println("false"); false
}
def b2 = {
  println("true"); true
}

b1 && b2
b1 & b2
