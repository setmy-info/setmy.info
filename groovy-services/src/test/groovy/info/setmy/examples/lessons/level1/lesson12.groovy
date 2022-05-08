println("============ LESSON 12 ==============")

record Point(int x = 1, int y = 1) {}

def p1 = new Point(0, 0)
def p2 = new Point(2, 4)
def p3 = new Point(0, 0)
def p4 = new Point()
def p5 = new Point(1, 1)

assert p1.x() == 0
assert p1.y() == 0
assert p2.x() == 2
assert p2.y() == 4
assert p1.toString() == 'Point[x=0, y=0]'
assert p2.toString() == 'Point[x=2, y=4]'
assert p1.hashCode() == 0
assert p2.hashCode() == 66
assert p3.hashCode() == 0
assert p1 == p3
assert p4 == p5
