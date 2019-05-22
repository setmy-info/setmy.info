import os

from flask import Flask

from setmy.info.config import Config
from setmy.info.logger import Logger
from setmy.info.environment import Environment
from setmy.info.commandLine import CommandLine
from setmy.info.constants import ENVIRONMENT

global system


class System:

    def __init__(self):
        self.app = None
        self.conf = Config()
        self.logger = Logger()
        self.environment = Environment()
        self.commandLine = CommandLine()

    def init(self):
        self.environment.load()
        self.conf.load()
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
