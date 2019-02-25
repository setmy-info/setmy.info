# New ports collection makefile for:	setmy-info-scripts
# Date Created:				2019.02.19
# Whom:					Imre Tabur <imre.tabur@eesti.ee>
#
# $FreeBSD$
#

PORTNAME=      setmy-info-scripts
PORTVERSION=   0.1.0
PORTREVISION=  0
CATEGORIES=    devel
MASTER_SITES=  ftp://example.com ftp://example.com

MAINTAINER=    imre.tabur@eesti.ee
COMMENT=       Example comment

MANCOMPRESSED= yes

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

dist: $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz install-dist

$(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz: $(PORTNAME)-$(PORTVERSION)
	$(TAR) --exclude .hg --exclude 'resources/port' -cvzf $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz ./$(PORTNAME)-$(PORTVERSION)

$(PORTNAME)-$(PORTVERSION):
	@$(ECHO_MSG) "Creating source folder $(PORTNAME)-$(PORTVERSION)"
	mkdir -p $(PORTNAME)-$(PORTVERSION)
	@$(ECHO_MSG) "Copy all source to $(PORTNAME)-$(PORTVERSION)"
	cd $(PORTNAME)-$(PORTVERSION);ls -la

install-dist:
	$(CP) $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz $(PORTSDIR)/distfiles

clean-tar:
	$(RM) -f $(PORTNAME)-$(PORTVERSION)-$(PORTREVISION).src.tar.gz

clean-co:
	$(RM) -fR  $(PORTNAME)-$(PORTVERSION)

clean-dist: clean-tar clean-co

.PHONY : clean-tar dist clean-co clean-dist install-dist

.include <bsd.port.mk>
