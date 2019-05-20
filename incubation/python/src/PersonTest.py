'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''
import unittest
import Person

class Test(unittest.TestCase):

    def setUp(self):
        pass


    def tearDown(self):
        pass


    def testName(self):
        pass

    def testInitialState(self):
        person = Person.Person(lastName="Meeter")
        self.assertEqual(person.firstName, 'Imre')
        self.assertEqual(person.lastName, 'Meeter')

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()