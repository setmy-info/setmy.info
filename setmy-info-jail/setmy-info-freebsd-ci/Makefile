# Copyright Imre Tabur

JAIL_PROJECT_NAME="setmy-info-freebsd-ci"
JAIL_PROJECT_VERSION="13.0-RELEASE"

all: build

build:
	smi-jail JAIL=$(JAIL_PROJECT_NAME) TAG=$(JAIL_PROJECT_VERSION)

.PHONY: all build
