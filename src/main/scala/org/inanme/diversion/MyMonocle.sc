import monocle.Lens
import monocle.macros.GenLens

case class Street(number: Int, name: String)
case class Address(city: String, street: Street)
case class Company(name: String, address: Address)
case class Employee(name: String, company: Company)

val company: Lens[Employee, Company] = GenLens[Employee](_.company)
val address: Lens[Company, Address] = GenLens[Company](_.address)
val street: Lens[Address, Street] = GenLens[Address](_.street)
val streetName: Lens[Street, String] = GenLens[Street](_.name)

val streetNameLens = company composeLens address composeLens street composeLens streetName

val employee = Employee("john", Company("awesome inc", Address("london", Street(23, "high street"))))

streetNameLens.modify(_ ⇒ "mert")(employee)