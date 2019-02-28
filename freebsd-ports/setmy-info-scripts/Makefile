# $FreeBSD$

# https://www.freebsd.org/doc/en_US.ISO8859-1/books/porters-handbook/index.html
# https://www.freebsd.org/doc/en_US.ISO8859-1/books/porters-handbook/special.html#staging-ex1

PORTNAME=      setmy-info-scripts
PORTVERSION=   0.6.0
#PORTREVISION=  0
CATEGORIES=    devel
MASTER_SITES=  https://www.hearandseesystems.com/

MAINTAINER=    imre.tabur@eesti.ee
COMMENT=       Example comment

LICENSE=       MIT
LICENSE_FILE=	${WRKSRC}/LICENSE

MANCOMPRESSED= yes

HAS_CONFIGURE= yes

pre-clean:
	@${ECHO_MSG} "###pre-clean###"

post-clean:
	@${ECHO_MSG} "###post-clean###"

pre-configure:
	@${ECHO_MSG} "###pre-configure###"

post-configure:
	@${ECHO_MSG} "###post-configure###"

pre-build:
	@${ECHO_MSG} "###pre-build###"

post-build:
	@${ECHO_MSG} "###post-build###"

pre-install:
	@${ECHO_MSG} "###pre-install###"

post-install:
	@${ECHO_MSG} "###post-install###"

dist: ${PORTNAME}-${PORTVERSION}-${PORTREVISION}.src.tar.gz install-dist

${PORTNAME}-${PORTVERSION}-${PORTREVISION}.src.tar.gz: ${PORTNAME}-${PORTVERSION}
	${TAR} -cvzf ${PORTNAME}-${PORTVERSION}-${PORTREVISION}.src.tar.gz ./${PORTNAME}-${PORTVERSION}

${PORTNAME}-${PORTVERSION}-${PORTREVISION}.tar.gz: ${PORTNAME}-${PORTVERSION}
	${TAR} -cvzf ${PORTNAME}-${PORTVERSION}-${PORTREVISION}.tar.gz ./${PORTNAME}-${PORTVERSION}

${PORTNAME}-${PORTVERSION}:
	@${ECHO_MSG} "Creating source folder ${PORTNAME}-${PORTVERSION}"
	${MKDIR} -p ${PORTNAME}-${PORTVERSION}
	@${ECHO_MSG} "Copy all source to ${PORTNAME}-${PORTVERSION}"
	${CP} -Rf ../../setmy-info-scripts/* ${PORTNAME}-${PORTVERSION}

install-dist:
	${CP} ${PORTNAME}-${PORTVERSION}-${PORTREVISION}.src.tar.gz ${PORTSDIR}/distfiles/${PORTNAME}-${PORTVERSION}.tar.gz

clean-dist: clean-tar clean-co clean-working clean-distfiles

clean-tar:
	${RM} -f ${PORTNAME}-${PORTVERSION}-${PORTREVISION}.src.tar.gz

clean-co:
	${RM} -fR  ${PORTNAME}-${PORTVERSION}

clean-working:
	${RM} -fR work

clean-distfiles:
	${RM} -f ${PORTSDIR)/distfiles/${PORTNAME}-${PORTVERSION}.tar.gz

portlint:
	portlint

.PHONY : clean-tar dist clean-co clean-dist install-dist portlint clean-working clean-distfiles

.include <bsd.port.mk>
