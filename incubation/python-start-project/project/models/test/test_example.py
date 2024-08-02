'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@mail.ee>
'''
import unittest


class Test(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testName(self):
        self.assertEqual('Imre', 'Imre')
        pass


if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
