println("============ LESSON 4 ==============")

Map m = [Amazon: "AWS"].withDefault(Closure.IDENTITY)

println m.Amazon
println m.Google

def something(String value) {
    return value.reverse()
}

Map m2 = [Amazon: "AWS"].withDefault(this.&something)

println m2.Amazon
println m2.Google
