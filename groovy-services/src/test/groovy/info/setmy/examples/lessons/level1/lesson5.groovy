println("============ LESSON 5 ==============")

def str = "Lorem impsum dolor sit amet."

def result = (str =~ /\b[A-Za-z]/).replaceAll { "[${it.group()}]".toString() }

println result
