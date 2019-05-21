
PYTHONPATH:=./test:$(PYTHONPATH)
export PYTHONPATH

all:

tools:
	wget -c https://bootstrap.pypa.io/get-pip.py
	sudo python3 get-pip.py
	pip --version

prepare:
	pip install -r requirements.txt --user

run:
	./application

example:
	python3 ./src/example

test:
	python3 -m unittest discover -s ./src/ -p *Test.py

freeze:
	pip freeze > requirements.txt

.PHONY: all prepare run example test freeze
