'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''

import yaml

global conf

class Config:

    number = 0

    def inc(self):
        self.number += 1 

    def load(self):
        with open("./test/test.yml", 'r') as stream:
            try:
                print(yaml.safe_load(stream))
            except yaml.YAMLError as exc:
                print(exc)

conf = Config()
