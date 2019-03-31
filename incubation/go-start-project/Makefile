
CUR_DIR=$(shell pwd)
GOPATH=$(CUR_DIR)

GO=go
STRIP=strip
GOBUILD=$(GO) build
GOINSTALL=$(GO) install
GOCLEAN=$(GO) clean
GOTEST=$(GO) test
GOGET=$(GO) get
GOLIST=$(GO) list
GOPATH=$(CUR_DIR)
ECHO=echo
MODULES=example one two service

all: version pkglist install test modules

version:
	@$(ECHO) "=== Version ==="
	@$(GO) version
	@$(ECHO) Current directory: $(CUR_DIR)
	@$(ECHO) "GOPATH=$(GOPATH)"

pkglist:
	@$(ECHO) "=== Modules list ==="
	@$(GOLIST) ./...

install:
	@$(ECHO) "=== Installing ==="
	$(GOINSTALL) -v -gcflags "-N -l" ./... $(MODULES)
	@$(STRIP) ./bin/one
	@$(STRIP) ./bin/two
	@$(STRIP) ./bin/service

test:
	@$(ECHO) "=== Testing ==="
	$(GOTEST) -v -gcflags "-N -l" ./... $(MODULES)

clean:
	@$(ECHO) "=== Cleaning ==="
	$(GOCLEAN) -i -x $(MODULES)

modules: $(MODULES)

$(MODULES):
	@$(ECHO) $@

run:
	@./bin/one
	@./bin/two
	@./bin/service

.PHONY: all version pkglist install test modules
