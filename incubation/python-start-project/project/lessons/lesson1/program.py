# python -m project.lessons.lesson1.program
import math
import os
import sys


def main():
    print("Hello world")
    print(sys.platform)
    print(os.getcwd())
    print(2 ** 100)

    word = "spam, "
    print(word * 8)
    for character in "spam":
        print(character)

    txt = "Spam"
    print(type(txt))
    print(txt[0])
    print(txt[1:3])
    print(txt[1:])
    print(txt[0:3])
    print(txt[:3])
    print(txt.upper())
    print(txt.lower())

    txt = " one, two, three "
    separated = txt.strip().split(",")
    print(type(separated))
    print(separated)

    txt = "Späm"
    print(txt)
    for character in txt:
        print(character)

    txt = "Sp\xc4m"
    print(txt)
    for character in txt:
        print(character)

    int_number = 123 + 321
    print(int_number)
    print(type(int_number))

    floating_point = 1.5 * 2
    print(floating_point)
    print(len(str(floating_point)))
    print(type(floating_point))

    print(math.pi)
    print(type(math.pi))

    num_string = "123"
    print(int(num_string))

    num_string = "123.321"
    print(float(num_string))

    list_example = ["Text", 123, 321.123]
    print(type(list_example))
    print(list_example)

    dictionary_example = {"first": 1, "second": "2", "third": 3.3}
    dictionary_example["fourth"] = 4
    print(type(dictionary_example))
    print(dictionary_example)
    print(len(dictionary_example))

    tuple_example = (1, 2, 3, 4)
    print(type(tuple_example))
    print(tuple_example)
    print(len(tuple_example))

    set_example = {1, 2, 3, 4}
    print(type(set_example))
    print(set_example)
    print(len(set_example))

    print(math.e)

    print(type(True))
    print(True)
    print(False)
    print(None)

    string_value = "Hello"
    boolean_value = bool(string_value)
    print("This is: " + str(boolean_value))

    booleans = [bool('true'), bool('false'), bool(1), bool(0), bool('')]
    print(booleans)


if __name__ == "__main__":
    main()
