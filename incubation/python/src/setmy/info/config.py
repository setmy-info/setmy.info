'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''
from setmy.info.yamlLoader import YamlLoader
import os
from setmy.info.constants import CONFIG_FILE_NAME

global conf

basedir = os.path.abspath(os.path.dirname(__file__))

class Config:
    
    yamlLoader = YamlLoader()
    loaded = None

    DEBUG = False
    TESTING = False
    CSRF_ENABLED = True
    SECRET_KEY = 'this-really-needs-to-be-changed'

    # def __init__(self):
    #    '''Constuctor'''
    #    self.yamlLoader = YamlLoader()

    def load(self):
        self.loaded = self.yamlLoader.load(CONFIG_FILE_NAME)

    def path(self, pathName):
        return self.loaded['rest']['root'] + pathName


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
