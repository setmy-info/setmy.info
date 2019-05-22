'''
Created on 20. mai 2019

@author: Imre Tabur <imre.tabur@eesti.ee>
'''
import os
from flask import Flask
from flask import render_template
from flask import send_from_directory
from flask import request
from flask import jsonify
from setmy.info.config import conf, ENVIRONMENT
from setmy.info.rest.index import index
import werkzeug

app = Flask(__name__, template_folder="../templates", static_folder="../static", static_url_path='')
app.config.from_object(os.environ[ENVIRONMENT])
conf.load()


@app.route(conf.path("/"))
def idx():
    return index()


@app.route('/<name>')
def helloName(name):
    return "Hello {}!".format(name)


@app.route('/template/')
@app.route('/template/index')
def templates():
    userData = {'firstName': 'Imre'}
    return render_template('index.html', title='Microservice', data=userData)


# http://localhost:5000/static/index.html
@app.route('/static/<path:path>')
def staticContent(path):
    try:
        return send_from_directory('../static', path)
    except werkzeug.exceptions.NotFound as e:
        if path.endswith("/"):
            return send_from_directory('../static', path + "index.html")
        raise e


@app.route('/post', methods=['POST'])
def post():
    content = request.get_json()
    print (content)
    return jsonify(content)


if __name__ == '__main__':
    #app.run(host='0.0.0.0', port= 8090)
    app.run()
