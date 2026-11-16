RPMDIR   := rpm
TARGETS  := all rpms srpms tarball lint install upload upload-test upload-live \
            move move-test move-live deploy-test deploy-live clean distclean help

MAKEFLAGS += --no-print-directory

.PHONY: $(TARGETS)

$(TARGETS):
	$(MAKE) -C $(RPMDIR) $@
