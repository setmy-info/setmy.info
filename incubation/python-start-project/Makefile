
PYTHONPATH:=./src:./test:$(PYTHONPATH)
export PYTHONPATH

all:

tools:
	wget -c https://bootstrap.pypa.io/get-pip.py
	sudo python3 get-pip.py
	pip --version

prepare:
	pip install -r requirements.txt --user

run:
	./src/application

test:
	python3 -m unittest discover -s ./test/ -p *Test.py

freeze:
	pip freeze > requirements.txt

.PHONY: all prepare run test freeze
