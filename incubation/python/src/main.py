'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''
from flask import Flask
from setmy.info.config.Config import conf

app = Flask(__name__)

conf.load()

@app.route('/')
def hello():
    conf.inc()
    return "Hello World: " + str(conf.number) 

if __name__ == '__main__':
    app.run()
