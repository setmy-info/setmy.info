#!/bin/sh

# MIT License
# Copyright (c) 2017, 2018, 2019 Imre Tabur <imre.tabur@eesti.ee>

RELEASE=1.0.34
VERSION_TAG=version-${RELEASE}

npm install && npm run build && npm run test && npm run unit
hg commit -m "${VERSION_TAG}"
hg push
hg checkout default
hg merge develop
hg tag -a ${VERSION_TAG} -m "${VERSION_TAG}" && hg push && npm publish

exit 0
