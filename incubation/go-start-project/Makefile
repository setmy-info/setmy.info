
CUR_DIR=$(shell pwd)
GO=go
GOPATH=$(CUR_DIR)

all: version build

version:
	@$(GO) version
	echo $(CUR_DIR)
	echo "GOPATH=$(CUR_DIR)"

build:
	@$(GO) install -v -gcflags "-N -l" ./...
	@$(GO) build -v -gcflags "-N -l" ./...
	@$(GO) test -c -v -gcflags "-N -l" example
	@$(GO) test -v -gcflags "-N -l" ./...

clean:
	@$(GO) clean -i -x example

.PHONY: all build version
