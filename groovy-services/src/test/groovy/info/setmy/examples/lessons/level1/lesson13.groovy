import groovy.transform.builder.Builder

println("============ LESSON 13 ==============")

@Builder
class Person {
    String firstName
    String lastName
}

def person1 = new Person(firstName: "Imre", lastName: "Tabur")
def person2 = Person.builder().firstName("Imre").lastName("Tabur").build()

person1 == person2
person1 === person2
