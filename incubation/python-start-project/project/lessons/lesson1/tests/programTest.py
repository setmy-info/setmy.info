import unittest


# python -m project.lessons.lesson1.tests.programTest


class ProgramTest(unittest.TestCase):
    def test_something(self):
        self.assertEqual(True, True)


if __name__ == '__main__':
    unittest.main()
