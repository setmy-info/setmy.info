println("============ LESSON 2 ==============")

def names = ["John", "Mary", "Bob", "<mma", "cat", "Greg", "123"]

def result = names
        .findAll { it ==~ /[A-Z][a-z]+/ }
        .collect { it.toUpperCase() }
        .sort { String a, String b -> a.length() <=> b.length() ?: a <=> b }

println result

result.each { println it }

result.eachWithIndex { String entry, int index -> println "$entry, $index" }