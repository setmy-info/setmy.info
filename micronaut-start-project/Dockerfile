FROM setmyinfo/setmy-info-centos-java:v11.0.2

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Micronaut service base" \
      org.label-schema.version="1.2.0-SNAPSHOT" \
      org.label-schema.description="setmy.info Docker HUB CentOS Micronaut service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

RUN mkdir -p /opt/has /opt/has/bin /opt/has/lib /var/opt/has /var/opt/has/microservice
COPY target/micronaut-start-project-1.2.0-SNAPSHOT.jar /opt/has/lib/app.jar
RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN chown -R root:root                  /opt/has
RUN chown -R microservice:microservice  /var/opt/has
RUN ls /dev/urandom

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/urandom"

WORKDIR /var/opt/has/microservice

EXPOSE 8080/tcp
EXPOSE 8443/tcp

CMD ["/bin/sh"]
CMD java ${JAVA_OPTS} -jar /opt/has/lib/app.jar
