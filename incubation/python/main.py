'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''

from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello():
    return "Hello World!"

if __name__ == '__main__':
    app.run()
