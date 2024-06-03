FROM setmyinfo/setmy-info-rocky-java:latest

MAINTAINER Imre Tabur "imre.tabur@mail.ee"

LABEL org.label-schema.name="Docker HUB Rocky Spring boot micro service base" \
      org.label-schema.version="0.0.1-SNAPSHOT" \
      org.label-schema.description="setmy.info Docker HUB Centos Spring boot micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

COPY ../../../target/springboot-start-project-0.0.1-SNAPSHOT.jar /opt/setmy.info/lib/microservice.jar
RUN chown -R root:root /opt/setmy.info/lib/microservice.jar && \
    ls -la /opt/setmy.info/lib/microservice.jar && \
    java -version && \
    ls /dev/urandom

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/urandom"
ENV MICROSERVICE_LOG_DIR_PATH=/var/opt/setmy.info/microservice/log
ENV MICROSERVICE_LOG_FILE_NAME=microservice.log
ENV MICROSERVICE_PROFILES=live
ENV MICROSERVICE_OPTIONAL_CONFIG_FILE_PATH=/opt/setmy.info/etc/optional-microservice.yaml
ENV MICROSERVICE_JAR_FILE_PATH=/opt/setmy.info/lib/microservice.jar

WORKDIR /var/opt/setmy.info/microservice

EXPOSE 8080/tcp
EXPOSE 8443/tcp

USER microservice
#CMD java ${JAVA_OPTS} -jar /opt/setmy.info/lib/microservice.jar
CMD java-execute
