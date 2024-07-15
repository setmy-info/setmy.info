println("============ LESSON 5 ==============")

def str = "Lorem ipsum dolor sit amet."

def result = (str =~ /\b[A-Za-z]/).replaceAll { "[${it.group()}]".toString() }

println result

assert result == "[L]orem [i]psum [d]olor [s]it [a]met."
