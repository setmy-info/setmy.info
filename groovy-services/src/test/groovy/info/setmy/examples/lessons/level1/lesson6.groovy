println("============ LESSON 6 ==============")

abstract class Something {
    abstract void foo()

    abstract void bar()
}

Something something = {
    println "Foo!"
} as Something

something.foo()
something.bar()

abstract class Something2 {
    abstract void foo()

    abstract void bar()
}

Something2 something2 = [
        foo: {
            println "Foo fixed"
        },
        bar: {
            println "Bar fixed"
        }
] as Something2

something2.foo()
something2.bar()

Runnable runnable = {
    println "I'm runnable!"
} as Runnable

runnable.run()
