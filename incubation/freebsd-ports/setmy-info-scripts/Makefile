# New ports collection makefile for:	setmy-info-scripts
# Date Created:				2019.02.19
# Whom:					Imre Tabur <imre.tabur@eesti.ee>
#
# $FreeBSD$
#

PORTNAME=      setmy-info-scripts
PORTVERSION=   1.0.0
PORTREVISION=  0
CATEGORIES=    devel
MASTER_SITES=  ftp://example.com ftp://example.com

MAINTAINER=    imre.tabur@eesti.ee
COMMENT=       Exampple comment

MANCOMPRESSED= yes

HAS_BASE_REPO_URL=ssh://has@192.168.122.118//tank/shares/orgs/has/sources/projects/solutions/$(PORTNAME)

UNAME := $(shell uname -s)
ifeq ($(UNAME),Linux)
TAR=tar
CP=cp
endif

pre-clean:
	@$(ECHO_MSG) "###pre-clean###"

post-clean:
	@$(ECHO_MSG) "###post-clean###"

pre-configure:
	@$(ECHO_MSG) "###pre-configure###"

post-configure:
	@$(ECHO_MSG) "###post-configure###"

pre-build: dist
	@$(ECHO_MSG) "###pre-build###"

post-build:
	@$(ECHO_MSG) "###post-build###"

pre-install:
	@$(ECHO_MSG) "###pre-install###"

post-install:
	@$(ECHO_MSG) "###post-install###"

$(PORTNAME)-$(PORTVERSION):
	hg clone $(HAS_BASE_REPO_URL) $(PORTNAME)-$(PORTVERSION)
	cd $(PORTNAME)-$(PORTVERSION);hg update version-$(PORTVERSION)

co: $(PORTNAME)-$(PORTVERSION)

$(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz: co
	$(TAR) --exclude .hg -cvzf $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz ./$(PORTNAME)-$(PORTVERSION)

tar: $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz

install-dist:
ifeq ($(UNAME),FreeBSD)
	$(CP) $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz $(PORTSDIR)/distfiles
endif
ifeq ($(UNAME),Linux)
	$(CP) $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz ~/rpmbuild/SOURCES
	$(CP) ./$(PORTNAME)-$(PORTVERSION)/$(PORTNAME).spec ~/rpmbuild/SPECS
endif

dist: tar install-dist

clean-tar:
	$(RM) -f $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz

clean-co:
	$(RM) -fR  $(PORTNAME)-$(PORTVERSION)

clean-dist: clean-tar clean-co

ifeq ($(UNAME),Linux)
build:
	cd ~/rpmbuild/SOURCES; rpmbuild --target noarch --clean -ta $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz
endif

.PHONY : clean-tar co tar dist clean-co clean-dist install-dist

ifeq ($(UNAME),FreeBSD)
.include <bsd.port.mk>
endif
