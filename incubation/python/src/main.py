'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''
from flask import Flask
from setmy.info.config import conf
from setmy.info.rest.index import index

app = Flask(__name__)
conf.load()


@app.route(conf.path("/"))
def idx():
    return index()


if __name__ == '__main__':
    app.run()
