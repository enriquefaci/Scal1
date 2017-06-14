import scalaz._
import Scalaz._
import Lens._

case class Address(street: String, city: String, state: String, zip: Int)
case class Person(firstName: String, lastName: String, address: Address)

case class Department(name: String, numberOfEmployees: Int)
case class Company(departments: Map[Int, Department])

// creating lens with lensu
val streetL : Address @> String   = lensu((a,s) => a.copy(street = s), _.street)
val zipL    : Address @> Int      = lensu((a,z) => a.copy(zip = z), _.zip)

// alternative method to create lens: lensg
val addressL : Person @> Address  = lensg( p => a => p.copy(address = a), _.address)

// composing lenses
// Person @> Int
val personZipL = addressL >=> zipL // same as andThen
val personZipL2 = addressL andThen zipL


// From Lens to State

// Set value :State[Person, Address]
def setAddress(a :Address) = addressL := a

// modify value
val incrementZipCode = addressL >=> zipL %= (_+1)
// same using numeric modify +=
val incrementZipCode2 = addressL >=> zipL += 1


// Lenses and Maps
val numberOfEmployeesL : Department @> Int = lensu((d,c) => d.copy(numberOfEmployees = c), _.numberOfEmployees)
val departmentsL: Company @>  Map[Int, Department] = lensu((c,d) => c.copy(departments = d), _.departments)

// modify existing map entry. Will throw exception if key not found
def addEmployee(depId: Int) = departmentsL.at(depId) >=> numberOfEmployeesL += 1

//same but using optional
def addEmployee2(depId: Int) = departmentsL.member(depId) %= {
  case Some(d) => Some(numberOfEmployeesL.set(d, d.numberOfEmployees + 1))
  case None    => Some(Department("New Department", 1))
}

