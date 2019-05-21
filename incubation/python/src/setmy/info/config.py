'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''

import yaml

global conf

CONFIG_FILE_NAME = "./config/application.yml"


class YamlLoader:

    def load(self):
        with open(CONFIG_FILE_NAME, 'r') as stream:
            try:
                self.loaded = yaml.safe_load(stream)
                val = self.loaded['test']['secondLevel']['and']
                print('Loaded: ' + val)
                print(self.loaded)
            except yaml.YAMLError as exc:
                print(exc)
        return self.loaded

    def path(self, pathName):
        return self.loaded['rest']['root'] + pathName


conf = YamlLoader()

