#!/bin/sh

# Copyright Imre Tabur

DOCKER_PROJECT_NAME="setmy-info-centos-node"
DOCKER_PROJECT_VERSION="v10.15.0"
DOCKER_ID_USER="xxxxxx"
DOCKER_ID_ORGANIZATION="setmyinfo"
DOCKER_CONTENT_TRUST=1
NODE_VERSION=10.15.0
NODE_DIR_NAME=node-v${NODE_VERSION}-linux-x64
NODE_TAR_FILE_NAME=$NODE_DIR_NAME.tar.xz

wget -c https://nodejs.org/dist/v${NODE_VERSION}/$NODE_TAR_FILE_NAME -O $NODE_TAR_FILE_NAME
tar xvJf $NODE_TAR_FILE_NAME

docker build -t  "$DOCKER_ID_ORGANIZATION/$DOCKER_PROJECT_NAME:$DOCKER_PROJECT_VERSION" .
docker image tag "$DOCKER_ID_ORGANIZATION/$DOCKER_PROJECT_NAME:$DOCKER_PROJECT_VERSION" $DOCKER_ID_ORGANIZATION/$DOCKER_PROJECT_NAME:latest
docker images
docker login
docker image push $DOCKER_ID_ORGANIZATION/$DOCKER_PROJECT_NAME:latest
docker image push $DOCKER_ID_ORGANIZATION/$DOCKER_PROJECT_NAME:$DOCKER_PROJECT_VERSION

exit ${?}
