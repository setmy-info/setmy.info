package info.setmy.examples.lessons.level1

import groovy.transform.*

import java.time.LocalDate

import static java.time.LocalDate.of

println("============ LESSON 18 ==============")

//@Builder // Groovy immutable @Immutable
@ToString
@Immutable
@MapConstructor
@TupleConstructor
@EqualsAndHashCode
class Example {
    String name, email
    LocalDate dateTime

    static ExampleBuilder builder() {
        return new ExampleBuilder()
    }
}

class ExampleBuilder {
    String name
    String email
    LocalDate dateTime

    ExampleBuilder example(Example example) {
        this.name = example.name
        this.email = example.email
        this.dateTime = example.dateTime
        return this
    }

    ExampleBuilder name(String name) {
        this.name = name
        return this
    }

    ExampleBuilder email(String email) {
        this.email = email
        return this
    }

    ExampleBuilder dateTime(LocalDate dateTime) {
        this.dateTime = dateTime
        return this
    }

    Example build() {
        return new Example(name, email, dateTime)
    }
}

def name = "Imre"
def email = "imreit@hot.ee"
def dateTime = of(2024, 07, 13)
def map = ["name": name, "email": email, "dateTime": dateTime]

def example1 = new Example(name, email, dateTime)
def example1String = example1.toString()

def example2 = new Example(name: name, email: email, dateTime: dateTime)
def example2String = example2.toString()

def example3 = new Example(map)
def example3String = example3.toString()

def example4 = Example.builder()
        .name(name)
        .email(email)
        .dateTime(dateTime)
        .build()
def example4String = example4.toString()

def example5 = Example.builder()
        .example(example4)
        .name("John Doe")
        .email("john.doe@example.com")
        .build()
def example5String = example5.toString()

def different = new Example(name: "Peter", email: "peter@example.com", dateTime: dateTime)

assert example1 != null
assert example1 == example2
assert example2 == example3
assert example3 == example4
assert example4 != different
assert example5 != example3

assert example1String == "info.setmy.examples.lessons.level1.Example(Imre, imreit@hot.ee, 2024-07-13)"
assert example2String == "info.setmy.examples.lessons.level1.Example(Imre, imreit@hot.ee, 2024-07-13)"
assert example3String == "info.setmy.examples.lessons.level1.Example(Imre, imreit@hot.ee, 2024-07-13)"
assert example4String == "info.setmy.examples.lessons.level1.Example(Imre, imreit@hot.ee, 2024-07-13)"
assert different.toString() == "info.setmy.examples.lessons.level1.Example(Peter, peter@example.com, 2024-07-13)"
assert example5String == "info.setmy.examples.lessons.level1.Example(John Doe, john.doe@example.com, 2024-07-13)"
