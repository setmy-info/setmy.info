FROM setmyinfo/setmy-info-rocky-java:latest

# http://label-schema.org/rc1/ replaced with https://github.com/opencontainers/image-spec/blob/main/annotations.md
LABEL org.opencontainers.image.title="Docker HUB Rocky Spring boot micro service base" \
      org.opencontainers.image.description="setmy.info Docker HUB Centos Spring boot micro service base" \
      org.opencontainers.image.version="0.0.1-SNAPSHOT" \
      org.opencontainers.image.revision="" \
      org.opencontainers.image.licenses="MIT" \
      org.opencontainers.image.vendor="Hear And See Systems LLC" \
      org.opencontainers.image.created=$BUILD_DATE \
      org.opencontainers.image.documentation="" \
      org.opencontainers.image.authors="Imre Tabur <imre.tabur@mail.ee>" \
      org.opencontainers.image.source="https://github.com/setmy-info/setmy.info/tree/master/springboot-start-project" \
      org.opencontainers.image.url="https://github.com/setmy-info/setmy.info/tree/master/springboot-start-project" \
      org.opencontainers.image.ref.name="" \
      org.opencontainers.image.base.name=""

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
