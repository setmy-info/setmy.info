package info.setmy.examples.lessons.level1

println("============ LESSON 17 ==============")

def optionalBoolean = Optional.of(true);
assert optionalBoolean
optionalBoolean.ifPresent { println("Is ${it}") }

def optionalBoolean2 = Optional.empty();
assert !optionalBoolean2
optionalBoolean2.ifPresentOrElse { println("Is ${it}") } { println("None") }
