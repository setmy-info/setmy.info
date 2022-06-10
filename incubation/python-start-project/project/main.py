from project.models.person import Person


def main():
    person = Person()
    print("Hello world from " + person.get_name())


if __name__ == "__main__":
    main()
