println("============ LESSON 7 ==============")

def sum = (0..20).inject(0) { acc, n -> acc + n }

println sum

def product = (1..10).inject(1) { acc, n -> acc * n }

println product

def sum2 = (0..10).inject(7) { accumulated, n ->
    println "accumulated=$accumulated n=$n"
    accumulated + n
}

println sum2

def array = [1, 3, 7, 2, 5, 4, 6]
def result = array.inject(0) {
    accumulated, n ->
        println "accumulated=$accumulated n=$n"
        accumulated + n
}

println result
