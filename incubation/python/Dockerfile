FROM setmyinfo/setmy-info-centos:v1.0.4

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Python micro service base" \
      org.label-schema.version="v3.4" \
      org.label-schema.description="setmy.info Docker HUB Centos Python Flask micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

#ENV http_proxy http://cache.example.com:8080
#ENV https_proxy http://cache.example.com:8080

RUN yum -y update
RUN yum -y install python34
RUN python34 --version

RUN mkdir -p /opt/has /opt/has/bin /opt/has/lib /var/opt/has /var/opt/has/microservice

RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN chown -R root:root                  /opt/has
RUN chown -R microservice:microservice  /var/opt/has

EXPOSE 3000

CMD cd /opt/has/src && npm start
