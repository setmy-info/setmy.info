from setmy.info.system import system

from flask import render_template
from flask import send_from_directory
from flask import request
from flask import jsonify
from setmy.info.rest.index import index
import werkzeug

system.init()

@system.app.route(system.conf.path("/"))
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
    try:
        return send_from_directory('../static', path)
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
    system.app.run(host='0.0.0.0', port=5000)

