# Copyright Imre Tabur

DOCKER_PROJECT_NAME="springboot-start-project"
DOCKER_PROJECT_VERSION="1.2.0-SNAPSHOT"
DOCKER_ID_ORGANIZATION="setmyinfo"

all: build image show-images

build:
	docker build -t  "$(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):$(DOCKER_PROJECT_VERSION)" .

image:
	docker image tag "$(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):$(DOCKER_PROJECT_VERSION)" $(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):latest

show-images:
	docker images

run:
	docker run -d -p 8080:8080 --name $(DOCKER_PROJECT_NAME) "$(DOCKER_ID_ORGANIZATION)/$(DOCKER_PROJECT_NAME):$(DOCKER_PROJECT_VERSION)"

.PHONY: all build image show-images run
