
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
	python3 ./example

test:
	python3 unittest rexxxxxx

.PHONY: all prepare run example test