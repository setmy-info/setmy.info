# Copyright Imre Tabur

DOCKER_PROJECT_NAME="setmy-info-centos"
DOCKER_PROJECT_VERSION="v1.0.2"
DOCKER_ID_USER="xxxxxx"
DOCKER_ID_ORGANIZATION="setmyinfo"
DOCKER_CONTENT_TRUST=1

all: build image show-images login push

build:
	docker build -t  "$(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):$(DOCKER_PROJECT_VERSION)" .

image:
	docker image tag "$(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):$(DOCKER_PROJECT_VERSION)" $(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):latest

show-images:
	docker images

login:
	echo docker login

push: build image show-images login push
	echo docker image push $(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):latest
	echo docker image push $(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME:$(DOCKER_PROJECT_VERSION)

.PHONY: build image show-images login push
