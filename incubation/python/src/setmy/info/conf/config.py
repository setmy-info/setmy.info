import logging

from setmy.info.constants.loggingConstants import LOG_FORMAT, LOG_FILE_NAME, LOG_DIRECTORY
from setmy.info.constants.applicationConstants import CONFIG_FILE_NAME
from setmy.info.services.yamlLoader import YamlLoader


class Config:

    # Host
    host = '0.0.0.0'
    port = 5000

    # REST
    restRoot = ''

    # Logging
    logDirectory = LOG_DIRECTORY
    logFileName = LOG_FILE_NAME
    logLevel = logging.DEBUG
    logFormat = LOG_FORMAT

    def path(self, path):
        ''' Load '''
        return self.restRoot + path


class EnvironmentConfig(Config):

    def __init__(self):
        '''
        Constructor
        '''       

    def load(self):
        ''' Load '''


class FileConfig(Config):

    loaded = None
    yamlLoader = YamlLoader()

    def load(self):
        self.loaded = self.yamlLoader.load(CONFIG_FILE_NAME) 


class CommandLineConfig(Config):

    def __init__(self):
        '''
        Constructor
        '''       

    def handle(self):
        ''' Handle '''
