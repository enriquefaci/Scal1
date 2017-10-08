import scala.collection.mutable


//+ is used for adding an element to an unordered collection
//+: and :+ add an element to the beginning or end of an ordered collection.

1 :: List(1, 2, 3)
Set(1, 2, 3) + 1
3 #:: Stream(1, 2, 3)

List(1, 2, 3) ::: List(0, -1)
List(1, 2, 3) ++ List(0, -1)

mutable.ArrayBuffer(1, 2, 3)


Map(1 → 2, 2 → 3) - 2
Set(1, 2, 3).map(_.toString).max

var x = mutable.ArrayBuffer(1, 2, 3)

