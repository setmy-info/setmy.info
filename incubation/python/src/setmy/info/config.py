'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''

import yaml
import logging
import os

global ENVIRONMENT
global conf

CONFIG_FILE_NAME = "./config/application.yml"
FORMAT = '%(asctime)-15s %(message)s'
ENVIRONMENT = 'ENVIRONMENT'

basedir = os.path.abspath(os.path.dirname(__file__))
logging.basicConfig(filename='log.log', format=FORMAT, level=logging.DEBUG)

class Config(object):
    DEBUG = False
    TESTING = False
    CSRF_ENABLED = True
    SECRET_KEY = 'this-really-needs-to-be-changed'

    def __init__(self):
        self.yamlLoader = YamlLoader()

    def load(self):
        self.yamlLoader.load()

    def path(self, pathName):
        return self.yamlLoader.loaded['rest']['root'] + pathName


class ProductionConfig(Config):
    DEBUG = False


class StagingConfig(Config):
    DEVELOPMENT = True
    DEBUG = True


class DevelopmentConfig(Config):
    DEVELOPMENT = True
    DEBUG = True


class TestingConfig(Config):
    TESTING = True


class YamlLoader:

    def load(self):
        # format=FORMAT, 
        #logging.basicConfig(filename='log.log', format=FORMAT, level=logging.DEBUG)
        logging.info(ENVIRONMENT + '=' + os.environ[ENVIRONMENT])
        logging.warning('### Watch out!')
        logging.info('### I told you so')
        logging.debug('### This message should go to the log file')
        logging.info('### Name: ' + __name__ + ' : ' + str((__name__ == '__main__') == True))
        with open(CONFIG_FILE_NAME, 'r') as stream:
            try:
                self.loaded = yaml.safe_load(stream)
                val = self.loaded['test']['secondLevel']['and']
                print('Loaded: ' + val)
                print(self.loaded)
            except yaml.YAMLError as exc:
                print(exc)
        return self.loaded


conf = Config()
