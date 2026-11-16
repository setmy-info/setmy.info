# RPMs of springboot-start-app, the same layout as the smi-*-config projects: one tarball, one spec per
# environment, smi-deploy (setmy-info-scripts) for the upload and the hand-over to the deployment service.
#
# The jar is built by Maven before this Makefile runs (mvn install), make only packages it.
#
# No machine is named here: the servers come from the environment, normally the Jenkins job configuration,
# so the project stays generic for whoever clones it:
#
#   SMI_DEPLOY_TEST_HOSTS   TEST servers,  [user@]host[:port], several separated by spaces or commas
#   SMI_DEPLOY_LIVE_HOSTS   LIVE servers (BE), the same form
#   SMI_DEPLOY_USER, SMI_DEPLOY_PORT, SMI_DEPLOY_REMOTE_DIR, SMI_DEPLOY_INCOMING_DIR are read by smi-deploy itself

APP      := springboot-start-app
REPO     := $(abspath $(CURDIR)/..)
# Project version from pom.xml: the <version> line indented by 4 spaces is the project one, the parent one by 8
MAVEN_VERSION := $(shell sed -n 's|^    <version>\(.*\)</version>.*|\1|p' $(REPO)/pom.xml | head -1)
VERSION  := $(subst -SNAPSHOT,,$(MAVEN_VERSION))
# A snapshot gets release 0.<Jenkins build number>, so every CI build is an upgrade of the one before it,
# and a released version (no -SNAPSHOT) gets release 1
ifneq ($(findstring -SNAPSHOT,$(MAVEN_VERSION)),)
RELEASE  ?= 0.$(or $(BUILD_NUMBER),1)
else
RELEASE  ?= 1
endif
SRCNAME  := smi-$(APP)
TOPDIR   := $(shell rpm --eval '%{_topdir}')
STAGE    := $(CURDIR)/.stage
TARBALL  := $(TOPDIR)/SOURCES/$(SRCNAME)-$(VERSION).tar.gz

# The executable jar: spring-boot-maven-plugin repackages with the classifier "exec"
JAR      := $(REPO)/target/$(APP)-$(MAVEN_VERSION)-exec.jar
UNIT     := $(REPO)/src/main/systemd/$(APP).service

SPECS    := $(sort $(wildcard *.spec))
NAMES    := $(basename $(SPECS))
RPMS     := $(foreach n,$(NAMES),$(TOPDIR)/RPMS/noarch/$(n)-$(VERSION)-$(RELEASE).noarch.rpm)
SRPMS    := $(foreach n,$(NAMES),$(TOPDIR)/SRPMS/$(n)-$(VERSION)-$(RELEASE).src.rpm)

RPMBUILD := rpmbuild --quiet --define 'smi_version $(VERSION)' --define 'smi_release $(RELEASE)'

SKIP_LIVE := smi-$(APP)-test
SKIP_TEST := smi-$(APP)
RPMS_LIVE := $(filter-out $(foreach n,$(SKIP_LIVE),$(TOPDIR)/RPMS/noarch/$(n)-$(VERSION)-$(RELEASE).noarch.rpm),$(RPMS))
RPMS_TEST := $(filter-out $(foreach n,$(SKIP_TEST),$(TOPDIR)/RPMS/noarch/$(n)-$(VERSION)-$(RELEASE).noarch.rpm),$(RPMS))

SMI_DEPLOY_TEST_HOSTS ?=
SMI_DEPLOY_LIVE_HOSTS ?=

.PHONY: all rpms srpms tarball lint install upload upload-test upload-live \
        move move-test move-live deploy-test deploy-live clean distclean help

all: rpms

rpms: $(RPMS)

srpms: $(SRPMS)

tarball: $(TARBALL)

$(JAR):
	@echo "$(JAR) is missing: build it first with mvn install (in $(REPO) or the repository root)"; exit 1

$(TARBALL): $(JAR) $(UNIT) Makefile
	@mkdir -p $(TOPDIR)/SOURCES
	@rm -rf $(STAGE)
	@mkdir -p $(STAGE)/$(SRCNAME)-$(VERSION)
	cp -a $(JAR) $(STAGE)/$(SRCNAME)-$(VERSION)/$(APP).jar
	cp -a $(UNIT) $(STAGE)/$(SRCNAME)-$(VERSION)/$(APP).service
	tar -C $(STAGE) -czf $@ $(SRCNAME)-$(VERSION)
	@rm -rf $(STAGE)

$(TOPDIR)/RPMS/noarch/%-$(VERSION)-$(RELEASE).noarch.rpm: %.spec $(TARBALL)
	$(RPMBUILD) -bb $<

$(TOPDIR)/SRPMS/%-$(VERSION)-$(RELEASE).src.rpm: %.spec $(TARBALL)
	$(RPMBUILD) -bs $<

lint:
	@command -v rpmlint >/dev/null 2>&1 || { echo "rpmlint is missing: dnf install rpmlint"; exit 1; }
	rpmlint $(SPECS) $(RPMS)

install: rpms
	sudo dnf -y install $(RPMS_TEST)

upload: upload-test

# $(1) hosts, $(2) smi-deploy action, $(3) packages
define smi_deploy_to
@command -v smi-deploy >/dev/null 2>&1 || { \
    echo "smi-deploy is missing: install the setmy-info-scripts package"; exit 1; }
@[ -n "$(strip $(1))" ] || { \
    echo "no servers: set SMI_DEPLOY_TEST_HOSTS / SMI_DEPLOY_LIVE_HOSTS (Jenkins job configuration)"; exit 1; }
$(foreach p,$(3),SMI_DEPLOY_HOSTS="$(1)" smi-deploy $(2) $(p)
)
endef

upload-test: rpms
	$(call smi_deploy_to,$(SMI_DEPLOY_TEST_HOSTS),upload,$(RPMS_TEST))

upload-live: rpms
	$(call smi_deploy_to,$(SMI_DEPLOY_LIVE_HOSTS),upload,$(RPMS_LIVE))

move: move-test

move-test:
	$(call smi_deploy_to,$(SMI_DEPLOY_TEST_HOSTS),handover,$(notdir $(RPMS_TEST)))

move-live:
	$(call smi_deploy_to,$(SMI_DEPLOY_LIVE_HOSTS),handover,$(notdir $(RPMS_LIVE)))

deploy-test: upload-test move-test

deploy-live: upload-live move-live

clean:
	rm -rf $(STAGE)
	rm -f $(RPMS) $(SRPMS) $(TARBALL)

distclean: clean
	rm -rf $(TOPDIR)/BUILD/$(SRCNAME)-$(VERSION)

help:
	@echo "make              build the noarch RPMs from $(notdir $(JAR)) (mvn install first)"
	@echo "make srpms        build the source RPMs"
	@echo "make tarball      only refresh the source tarball"
	@echo "make lint         run rpmlint over the specs and the RPMs"
	@echo "make install      dnf install the TEST package here (uses sudo)"
	@echo "make upload-test  upload the TEST RPM to SMI_DEPLOY_TEST_HOSTS"
	@echo "make upload-live  upload the LIVE RPM to SMI_DEPLOY_LIVE_HOSTS"
	@echo "make move-test    hand the uploaded RPM to the deployment service of the TEST hosts, which installs it"
	@echo "make move-live    hand the uploaded RPM to the deployment service of the LIVE hosts, which installs it"
	@echo "make deploy-test  upload-test and move-test"
	@echo "make deploy-live  upload-live and move-live"
	@echo "make clean        remove the RPMs, SRPMs and the tarball"
	@echo "make distclean    also remove the rpmbuild work tree"
	@echo
	@echo "version:  $(VERSION)-$(RELEASE) (pom.xml $(MAVEN_VERSION))"
	@echo "packages: $(NAMES)"
	@echo "to live:  $(notdir $(RPMS_LIVE))"
	@echo "to test:  $(notdir $(RPMS_TEST))"
	@echo "test hosts: $(or $(SMI_DEPLOY_TEST_HOSTS),(SMI_DEPLOY_TEST_HOSTS not set))"
	@echo "live hosts: $(or $(SMI_DEPLOY_LIVE_HOSTS),(SMI_DEPLOY_LIVE_HOSTS not set))"
	@echo "output:   $(TOPDIR)/RPMS/noarch"
	@echo
	@echo "What reacts to the move on the server:"
	@echo "  setmy-info-deploy.path -> setmy-info-deploy.service -> smi-incoming-deploy"
	@echo "  journalctl -u setmy-info-deploy.service -n 50"
	@echo "  journalctl -u $(APP).service -n 50"
