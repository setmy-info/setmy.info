package info.setmy.examples.lessons.level1

import groovy.transform.*

println("============ LESSON 19 ==============")

@ToString
@Immutable
@MapConstructor
@TupleConstructor
@EqualsAndHashCode
class ExampleNumber {

    int number

    ExampleNumber plus(ExampleNumber another) {
        new ExampleNumber(this.number + another.number);
    }

    ExampleNumber minus(ExampleNumber another) {
        new ExampleNumber(this.number - another.number);
    }

    static ExampleNumberBuilder builder() {
        return new ExampleBuilder()
    }
}

class ExampleNumberBuilder {
    int number

    ExampleBuilder exampleNumber(ExampleNumber example) {
        this.number = example.number
        return this
    }

    Example build() {
        return new Example(number)
    }
}

def a = new ExampleNumber(2)
def b = new ExampleNumber(3)
def sum = a + b
def subtract = a - b

assert sum.number == 5
assert subtract.number == -1
