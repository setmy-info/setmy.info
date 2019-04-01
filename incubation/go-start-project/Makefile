PROJECT=go-start-project
GO=go
STRIP=strip
GOINSTALL=$(GO) install
GOCLEAN=$(GO) clean
GOTEST=$(GO) test
GOLIST=$(GO) list
ECHO=echo

CUR_DIR=$(shell pwd)
GOPATH=$(CUR_DIR)

APPS=one two service
PACKAGES=example $(APPS)

all: version pkglist build strip test

version:
	@$(ECHO) "=== Version ==="
	@$(GO) version
	@$(ECHO) Current directory: $(CUR_DIR)
	@$(ECHO) "GOPATH=$(GOPATH)"

pkglist:
	@$(ECHO) "=== PACKAGES list ==="
	@$(GOLIST) -e ./...

build:
	@$(ECHO) "=== Installing ==="
	$(GOINSTALL) -v -gcflags "-N -l" ./... $(PACKAGES)

strip: $(APPS)

$(APPS):
	@$(ECHO) "Striping ./bin/$@"
	@$(STRIP) ./bin/$@

test:
	@$(ECHO) "=== Testing ==="
	$(GOTEST) -v -gcflags "-N -l" ./... $(PACKAGES)

clean:
	@$(ECHO) "=== Cleaning ==="
	$(GOCLEAN) -i -x $(PACKAGES)

run:
	@./bin/one
	@./bin/two
	@./bin/service

docker:
	docker build -t setmyinfo/$(PROJECT) .

dockerrun:
	docker run -p 8040:8080 -d setmyinfo/$(PROJECT)

init:
#	@$(GO) get -u github.com/kardianos/govendor
#	govendor init

.PHONY: all version pkglist install strip $(APPS) test docker dockerrun init
