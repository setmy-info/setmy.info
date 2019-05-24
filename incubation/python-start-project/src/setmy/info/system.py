import os

from flask import Flask

from setmy.info.conf.config import Config
from setmy.info.conf.config import EnvironmentConfig
from setmy.info.conf.config import CommandLineConfig
from setmy.info.conf.config import FileConfig
from setmy.info.conf.loggerConfig import Logger
from setmy.info.constants.environmentConstants import ENVIRONMENT

global system


class System:
    app = None
    conf = Config()
    logger = Logger()
    fileConfig = FileConfig()
    environment = EnvironmentConfig()
    commandLine = CommandLineConfig()

    def init(self):
        self.environment.load()
        self.fileConfig.load()
        self.commandLine.handle()
        self.combineConfig()
        self.logger.init(self.conf)
        self.app = Flask(__name__, template_folder="../templates", static_folder="../static", static_url_path='')
        self.app.config.from_object(os.environ[ENVIRONMENT])

    def combineConfig(self):
        '''
        Combine configuration in order (overwriting previous):
            defaults,
            environment,
            yaml,
            commandLine
        '''


system = System()
