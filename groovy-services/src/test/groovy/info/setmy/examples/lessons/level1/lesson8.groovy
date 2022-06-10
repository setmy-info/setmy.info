println("============ LESSON 8 ==============")

def closure = {
    add(1)
    add(2)
}

def list = []

closure.delegate = list
closure.resolveStrategy = Closure.DELEGATE_ONLY
closure()

println list
