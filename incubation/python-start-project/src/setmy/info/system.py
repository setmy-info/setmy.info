import os

from flask import Flask
from setmy.info.services.applicationLoaderService import ApplicationLoaderService
import logging
from logging.handlers import RotatingFileHandler

global system


class System:

    ENVIRONMENT = 'ENVIRONMENT'

    def __init__(self):
        self.applicationLoaderService = ApplicationLoaderService.getInstance()

    def init(self):
        self.loadConfig()
        self.loggerInit(self.applicationProperties.log)
        self.flaskInit()

    def loadConfig(self):
        self.applicationProperties = self.applicationLoaderService.loadApplicationProperties()

    def loggerInit(self, log):
        logging.basicConfig(filename=log.directory + log.fileName , format=log.format, level=log.level)
        handler = RotatingFileHandler(filename=log.directory + log.fileName, maxBytes=log.size, backupCount=1)
        log = logging.getLogger()
        log.addHandler(handler)

    def flaskInit(self):
        self.app = Flask(__name__, template_folder=self.applicationProperties.server.templateFolder, static_folder=self.applicationProperties.server.staticFolder, static_url_path='')
        self.app.config.from_object(os.environ[System.ENVIRONMENT])

    def path(self, path):
        return self.applicationProperties.rest.root + path
    
    def getHost(self):
        return self.applicationProperties.server.host

    def getPort(self):
        return self.applicationProperties.server.port


system = System()
