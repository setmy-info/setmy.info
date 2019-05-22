'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''

import yaml
import logging

CONFIG_FILE_NAME = "./config/application.yml"
FORMAT = '%(asctime)-15s %(clientip)s %(user)-8s %(message)s'

global conf

class Config:

    def __init__(self):
        self.yamlLoader = YamlLoader()

    def load(self):
        self.yamlLoader.load()

    def path(self, pathName):
        return self.yamlLoader.loaded['rest']['root'] + pathName
    

class YamlLoader:

    def load(self):
        #format=FORMAT, 
        logging.basicConfig(level=logging.DEBUG)
        logging.warning('Watch out!')
        logging.info('I told you so')
        logging.debug('This message should go to the log file')
        logging.info('Name: ' + __name__ + ' : ' + str((__name__ == '__main__') == True))
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

