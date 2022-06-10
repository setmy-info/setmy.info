println("============ LESSON 1 ==============")
String myName = "Imre"

Closure<String> sayHello = { String name ->
    println "Hello, $name! My name is $myName"
    return "Done with hello for $name!"
}

//Explicit call
println sayHello.call("John")

// Implicit call
println sayHello("Mary")
