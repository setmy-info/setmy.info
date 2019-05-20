
all:

tools:
	wget -c https://bootstrap.pypa.io/get-pip.py
#	--user --proxy='${$http_proxy}'
	sudo python3 get-pip.py

prepare:
	pip install -r requirements.txt

run:
	./application

example:
	python3 ./src/example

test:
	python3 unittest rexxxxxx

freeze:
	pip freeze > requirements.txt

virtual:
#	python3 -m venv env
#	source env/bin/activate

deactivate:
	deactivate

.PHONY: all prepare run example test virtual freeze
