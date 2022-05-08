println("============ LESSON 9 ==============")

def fileName = "./temp-${UUID.randomUUID().toString()}"

new File(fileName) << """
${UUID.randomUUID().toString()}
${UUID.randomUUID().toString()}
${UUID.randomUUID().toString()}
""".trim()

new File(fileName).eachLine { println it }