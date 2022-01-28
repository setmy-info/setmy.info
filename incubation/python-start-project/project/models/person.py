class Person:
    """A simple person class"""
    name = "Boris Johnson"

    def __init__(self):
        print("Constructed specific class for almighty " + self.name)

    def get_name(self):
        return self.name
