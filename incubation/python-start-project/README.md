# python-start-project

## Setup

Install Python and set it to PATH.

Then:

```shell
py -m ensurepip --upgrade
```

Set the PIP in to the PATH variable as it shows.

Execute the app on Windows:

```shell
py -m src.application.main
```

Other commands used to build setup:

```shell
py -m venv ./venv
.\venv\Scripts\activate
pip install --upgrade pip
pip install schedule
pip freeze > requirements.txt
pip install -r requirements.txt
```

TODO

1. How to do so, that binaries are not commited into the git and requirements.txt contains python version too and that can be installed into venv?
