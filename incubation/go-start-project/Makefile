
CUR_DIR=$(shell pwd)
GO=go
GOBUILD=$(GO) build
GOINSTALL=$(GO) install
GOCLEAN=$(GO) clean
GOTEST=$(GO) test
GOGET=$(GO) test
GOPATH=$(CUR_DIR)
PACKAGES=main example
#TARGET=

all: version build

#sub: $(TARGET)

version:
	@$(GO) version
	@echo Current directory: $(CUR_DIR)
	@echo "GOPATH=$(CUR_DIR)"

build: install dobuild build-tests

install:
	$(GOINSTALL) -v -gcflags "-N -l" ./...

dobuild:
	$(GOBUILD) -v -gcflags "-N -l" ./...

build-tests:
	@$(MAKE) $(PACKAGES)
	$(GOTEST) -v -gcflags "-N -l" ./...

main:
	@echo Building: $(@)
#	@$(GOTEST) -c -v -gcflags "-N -l" $(@)

example:
	@echo Building: $(@)
	$(GOTEST) -c -v -gcflags "-N -l" $(@)

clean:
	$(GOCLEAN) -i -x example
	$(GOCLEAN)  -i -x main
#	Delete tests to

run:
	@./bin/main

.PHONY: all build version build-tests
