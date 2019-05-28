from setmy.info.system import system

from flask import render_template
from flask import send_from_directory
from flask import request
from flask import jsonify
from setmy.info.rest.index import index
import werkzeug
import logging

system.init()

@system.app.route(system.path("/"))
def idx():
    return index()


@system.app.route('/<name>')
def helloName(name):
    return "Hello {}!".format(name)


@system.app.route('/template/')
@system.app.route('/template/index')
def templates():
    userData = {'firstName': 'Imre'}
    return render_template('index.html', title='Microservice', data=userData)


# http://localhost:5000/static/index.html
@system.app.route('/static/<path:path>')
def staticContent(path):
    dirName = None
    pathName = None
    if path.endswith("/"):
        dirName = '../static'
        pathName = './' + path + 'index.html'
    else:
        dirName = '../static/'
        pathName = path + '/index.html'
    try:
        logging.debug('Dir Name: ' + dirName)
        logging.debug('Path Name: ' + pathName)
        return send_from_directory(dirName, pathName)
    except werkzeug.exceptions.NotFound as e:
        if path.endswith("/"):
            return send_from_directory('../static', path + "index.html")
        raise e


@system.app.route('/post', methods=['POST'])
def post():
    content = request.get_json()
    print (content)
    return jsonify(content)


if __name__ == '__main__':
    system.app.run(host=system.getHost(), port=system.getPort())
