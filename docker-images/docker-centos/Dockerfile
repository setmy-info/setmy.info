FROM centos:7

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB Centos" \
      org.label-schema.version="v1.2.0" \
      org.label-schema.description="setmy.info Docker HUB Centos" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

RUN yum -y update
RUN mkdir -p /opt/has/{bin,lib,doc,man,etc,var}
RUN chown -R root:root /opt/has && \
    chmod -R u+rwx /opt/has && \
    chmod -R go+rx /opt/has && \
    chmod -R go-w /opt/has && \
    cd /opt && ls -la && \
    cd /opt/has && ls -la
