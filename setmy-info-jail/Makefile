JAIL_FOLDER =	\
		setmy-info-base-freebsd \
		setmy-info-freebsd \
		setmy-info-freebsd-nginx \
		setmy-info-freebsd-java \
		setmy-info-freebsd-tomcat \
		setmy-info-freebsd-ci \
		setmy-info-freebsd-infinispan \
		setmy-info-freebsd-mq \
		setmy-info-freebsd-node

all: $(JAIL_FOLDER)

$(JAIL_FOLDER):
	@cd $@; pwd; $(MAKE)

.PHONY: all $(JAIL_FOLDER)
