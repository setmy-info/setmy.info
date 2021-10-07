FROM centos:8.3.2011

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

# http://label-schema.org/rc1/
LABEL org.label-schema.name="Docker HUB Centos" \
      org.label-schema.version="8.3.2011" \
      org.label-schema.description="setmy.info Docker HUB Centos" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

RUN dnf -y update
RUN cat /etc/centos-release
RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN mkdir -p /opt/has/bin
RUN mkdir -p /opt/has/lib
RUN mkdir -p /opt/has/doc
RUN mkdir -p /opt/has/man
RUN mkdir -p /opt/has/etc
RUN mkdir -p /var/opt/has
RUN mkdir -p /var/opt/has/microservice
RUN chown -R root:root /opt/has
RUN chown -R microservice:microservice /var/opt/has

RUN chmod -R u+rwx /opt/has
RUN chmod -R go+rx /opt/has
RUN chmod -R go-w  /opt/has
RUN cd /opt && ls -la && \
    cd /opt/has && ls -la && \
    cd /var/opt/ && ls -la && \
    cd /var/opt/has && ls -la