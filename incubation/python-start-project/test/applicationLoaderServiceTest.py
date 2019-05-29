import unittest

from setmy.info.services.applicationLoaderService import ApplicationLoaderService


class ApplicationLoaderServiceTest(unittest.TestCase):

    def setUp(self):
        self.applicationLoader = ApplicationLoaderService.getInstance()
        pass

    def tearDown(self):
        pass

    def testLoad(self):
        result = self.applicationLoader.load()
        self.assertEqual(result['server']['host'], '0.0.0.0')
        pass

    def testLoadApplicationProperties(self):
        applicationProperties = self.applicationLoader.loadApplicationProperties()
        self.assertEqual(applicationProperties.server.host, '0.0.0.0')
        self.assertEqual(applicationProperties.server.port, 5000)
        self.assertEqual(applicationProperties.log.level, 'DEBUG')
        self.assertEqual(applicationProperties.log.directory, './')
        self.assertEqual(applicationProperties.log.fileName, 'python-start-project.log')
        self.assertEqual(applicationProperties.log.format, '%(asctime)-15s %(message)s')
        self.assertEqual(applicationProperties.log.size, 1024)
        self.assertEqual(applicationProperties.rest.root, '')
        pass


if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
