
CUR_DIR=$(shell pwd)
GO=go
GOPATH=$(CUR_DIR)
PACKAGES=main example
#TARGET=

all: version build

sub: $(TARGET)

version:
	@$(GO) version
	@echo Current directory: $(CUR_DIR)
	@echo "GOPATH=$(CUR_DIR)"

build: install dobuild build-tests

install:
	@$(GO) install -v -gcflags "-N -l" ./...

dobuild:
	@$(GO) build -v -gcflags "-N -l" ./...

build-tests:
	@$(MAKE) $(PACKAGES)
	@$(GO) test -v -gcflags "-N -l" ./...

main:
	@echo Building: $(@)
	@$(GO) test -c -v -gcflags "-N -l" $(@)

example:
	@echo Building: $(@)
	@$(GO) test -c -v -gcflags "-N -l" $(@)

clean:
	@$(GO) clean -i -x example
	@$(GO) clean -i -x main
#	Delete tests to

run:
	@./bin/main

.PHONY: all build version build-tests
