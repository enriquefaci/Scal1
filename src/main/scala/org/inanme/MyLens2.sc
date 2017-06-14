import scalaz._

// crappy case model, lack of creativity
case class Account(userName: String, person: Person)

case class Person(firstName: String, lastName: String, address: List[Address], gender: Gender)

case class Gender(gender: String)

case class Address(street: String, number: Int, postalCode: PostalCode)

case class PostalCode(numberPart: Int, textPart: String)

val acc1 = Account("user123", Person("Jos", "Dirksen",
  List(Address("Street", 1, PostalCode(12, "ABC")),
    Address("Another", 2, PostalCode(21, "CDE"))),
  Gender("male")))


val acc2 = Account("user345", Person("Brigitte", "Rampelt",
  List(Address("Blaat", 31, PostalCode(67, "DEF")),
    Address("Foo", 12, PostalCode(45, "GHI"))),
  Gender("female")))


// when you now want to change something, say change the gender (just because we can) we need to start copying stuff
val acc1Copy = acc1.copy(
  person = acc1.person.copy(
    gender = Gender("something")
  )
)

val genderLens = Lens.lensu[Account, Gender](
  (account, gender) => account.copy(person = account.person.copy(gender = gender)),
  (account) => account.person.gender
)

// and with a lens we can now directly get the gender
genderLens.set(acc1, Gender("Blaat"))
