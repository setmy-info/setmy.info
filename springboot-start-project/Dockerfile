FROM setmyinfo/setmy-info-centos-java:latest

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Spring boot micro service base" \
      org.label-schema.version="1.0.0-SNAPSHOT" \
      org.label-schema.description="setmy.info Docker HUB Centos Spring boot micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

COPY target/springboot-start-project-1.0.0-SNAPSHOT.jar /opt/has/lib/app.jar
RUN chown -R root:root                  /opt/has/lib/app.jar
RUN ls -la /opt/has/lib/app.jar
RUN java -version
RUN ls /dev/urandom

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/urandom"

WORKDIR /var/opt/has/microservice

EXPOSE 8080/tcp
EXPOSE 8443/tcp

CMD java ${JAVA_OPTS} -jar /opt/has/lib/app.jar
