# python-start-project

## Project preparation

Install Python and set it to PATH.

### Directory layout

Prepare files structure

```shell
mkdir python-start-project
cd python-start-project
mkdir project
mkdir docs
mkdir scripts
echo "" > README.md
echo "" > TODO.md
echo "" > LICENSE
```

### Install PIP

On:

**Windows**
```shell
py -m ensurepip --upgrade
```

**\*nix**
```shell
python -m ensurepip --upgrade
```

Set the PIP in to the PATH variable as it shows.

### Prepare venv

To prepare and switch to virtual environment on:

**Windows**
```shell
py -m venv ./.venv
.\.venv\Scripts\activate
```
**\*nix**
```shell
python -m venv ./.venv
source ./.venv/bin/activate
```

Then install pip

```shell
pip install --upgrade pip
```
and dependencies (Django, Flask, numpy, pandas) like:

```shell
pip install PACKAGENAME
```

or pre saved packages with versions

```shell
pip install -r requirements.txt
```

After install make or update dependencies list:

```shell
pip freeze > requirements.txt
```

### Execute the app

Execute the app on:

**Windows**
```shell
py -m project.application.main
```
**\*nix**
```shell
python -m project.application.main
```

## Executing test

```shell
python -m unittest discover -s ./project/models/test -p test_*.py
```

## TODO
