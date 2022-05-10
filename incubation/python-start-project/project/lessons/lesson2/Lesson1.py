"""
Lesson 1: Closures example
"""


def you(name):
    me_variable = 'Me'

    def me():
        print(name, 'and', me_variable)

    me()


def get_closure():
    def inner(name):
        return 'My name is ' + name

    return inner


if __name__ == "__main__":
    you('You')
    my_name_creator = get_closure()
    print(my_name_creator('Imre'))
