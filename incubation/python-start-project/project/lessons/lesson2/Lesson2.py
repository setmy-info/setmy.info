"""
Lesson 2: lambda
"""


def add_ten(a): return a + 10


def get_lambda(n):
    return lambda a: a * n


if __name__ == "__main__":
    print(add_ten(6))
    lamb = get_lambda(5)
    print(lamb(3))
    def l1(x): return 2 * x
    def l2(x, y): return x + y
    print(l1(4))
    print(l2(4, 2))
