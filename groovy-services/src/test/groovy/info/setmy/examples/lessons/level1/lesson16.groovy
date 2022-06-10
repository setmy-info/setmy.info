import groovy.contracts.Ensures
import groovy.contracts.Invariant
import groovy.contracts.Requires

println("============ LESSON 16 ==============")

@Invariant({ speed >= 0 })
class Rocket {
    int speed = 0
    boolean started = false

    @Requires({ !started })
    Rocket startEngine() { tap { started = true } }

    @Requires({ started })
    Rocket stopEngine() { tap { started = false } }

    @Requires({ started })
    @Ensures({ old.speed < speed })
    Rocket accelerate(int value) { tap { speed += value } }
}
boolean wasThrown = false
def rocket = new Rocket()
try {
    rocket.accelerate(10)
} catch (org.apache.groovy.contracts.PreconditionViolation e) {
    wasThrown = true
}
assert wasThrown
rocket.startEngine()
rocket.accelerate(10)
