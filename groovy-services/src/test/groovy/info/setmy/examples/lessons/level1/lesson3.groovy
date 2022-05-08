println("============ LESSON 3 ==============")
// Curring, composition, implicit 'it', closure delegates, typesafe DSL

def multiplier = { a, b -> a * b }
def doubler = { 2 * it }
def doublerSecond = multiplier.curry(2) // Curring
def quadruple = doubler.andThen(doubler)
def quadruple2 = doubler >> doubler

def result1 = multiplier.call(2, 3)
def result2 = doubler(4)
def result3 = doublerSecond(6)
def result4 = quadruple(7)
def result5 = quadruple2(7)

println result1
println "$result2 $result3 $result4 $result5"
