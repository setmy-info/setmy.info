# Copyright Imre Tabur

JAIL_PROJECT_NAME="setmy-info-freebsd-infinispan"

INFINISPAN_VERSION=13.0.2
INFINISPAN_DIR_NAME=infinispan-server-$(INFINISPAN_VERSION).Final
INFINISPAN_ZIP_FILE_NAME=infinispan-server-$(INFINISPAN_VERSION).Final.zip

all: download unpack build

download: $(INFINISPAN_ZIP_FILE_NAME)

$(INFINISPAN_ZIP_FILE_NAME):
	wget -c https://downloads.jboss.org/infinispan/$(INFINISPAN_VERSION).Final/$(INFINISPAN_ZIP_FILE_NAME)

unpack: $(INFINISPAN_DIR_NAME)

$(INFINISPAN_DIR_NAME):
	unzip $(INFINISPAN_ZIP_FILE_NAME)

build:
	smi-jail JAIL=$(JAIL_PROJECT_NAME) TAG=$(INFINISPAN_VERSION)

.PHONY: all download unpack build
