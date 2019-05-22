# Python

## Installation

### CentOS

    yum install -y python3 (?)

### Fedora

### FreeBSD

    pkg install -y python3

## Frameworks
    Django
    Flask (http://flask.pocoo.org/)
    Falcon

    Jinja2 (Template engine : http://jinja.pocoo.org/)
    Chameleon (Template engine : https://chameleon.readthedocs.io/en/latest/)

## Package management

    wget -c https://bootstrap.pypa.io/get-pip.py
    python3 get-pip.py
        --user
            Under **~/.local/bin**
        --proxy='${$http_proxy}'
    pip --version

## Eclipse
        http://www.pydev.org/updates

## Project setup
    pyvenv-3.4 env
    source env/bin/activate
    (pip install --upgrade pip)
    touch main.py .gitignore README.md requirements.txt
    pip install Flask==1.0.2
    pip completion --bash >> ~/.profile
    pip freeze > requirements.txt
        pip install -r requirements.txt
    python -m unittest discover
    python -m unittest discover -s ./ -p *Test.py
    nano main.py
        ---
        from flask import Flask
        app = Flask(__name__)


        @app.route('/')
        def hello():
            return "Hello World!"

        if __name__ == '__main__':
            app.run()
        ---
    export FLASK_ENV=development
    python main.py
    deactivate

    PYTHONPATH for searchin modules, like PATH.

## See also

    [xxxx](https://pythonspot.com/)
    [xxxx](https://www.fullstackpython.com)
    [xxxx](https://pip.pypa.io/en/stable/installing/)
    [xxxx](https://pip.readthedocs.io/en/1.0/running-tests.html#how-to-run-tests)
    [xxxx](http://flask.pocoo.org/docs/1.0/quickstart/#a-minimal-application)
    [xxxx](https://docs.python.org/3/library/unittest.mock.html)
    [xxxx](https://docs.python.org/3.5/library/datatypes.html)
    [xxxx](https://www.bipm.org/en/measurement-units/)
    [xxxx](https://realpython.com/python-mock-library/)
    [Pip](https://pypi.org/)
    [Pip registering](https://pypi.org/account/register/)
    [Packaging vol 1](https://packaging.python.org/tutorials/packaging-projects/)
    [Packaging vol 2](https://packaging.python.org/)
    
    https://fossbytes.com/python-fastest-growing-programming-language/
    https://stackoverflow.blog/2017/09/14/python-growing-quickly/
