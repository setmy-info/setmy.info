FROM setmyinfo/setmy-info-centos:v1.0.4

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Golang micro service base" \
      org.label-schema.version="1.2.0-SNAPSHOT" \
      org.label-schema.description="setmy.info Docker HUB CentOS Golang micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

RUN mkdir -p /opt/has /opt/has/bin /opt/has/lib /var/opt/has /var/opt/has/microservice
COPY ./bin/service /opt/has/bin/service
RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN chown -R root:root                  /opt/has
RUN chown -R microservice:microservice  /var/opt/has
RUN ls /dev/urandom

WORKDIR /var/opt/has/microservice

EXPOSE 8080/tcp
EXPOSE 8443/tcp

CMD /opt/has/bin/service
