ARG APP_NAME
ARG VERSION
ARG BUILD_DATE
ARG VCS_REF

FROM setmyinfo/setmy-info-rocky-java:latest

ARG APP_NAME
ARG VERSION
ARG BUILD_DATE
ARG VCS_REF

LABEL org.opencontainers.image.title="Docker HUB Rocky Java Spring Boot microservice" \
      org.opencontainers.image.description="setmy.info Docker HUB Rocky Java Spring Boot microservice" \
      org.opencontainers.image.version="${VERSION}" \
      org.opencontainers.image.revision="${VCS_REF}" \
      org.opencontainers.image.created="${BUILD_DATE}" \
      org.opencontainers.image.documentation="https://github.com/setmy-info/setmy.info/blob/master/springboot-start-project/springboot-start-app/README.md" \
      org.opencontainers.image.source="https://github.com/setmy-info/setmy.info/tree/master/springboot-start-project/springboot-start-app" \
      org.opencontainers.image.url="https://github.com/setmy-info/setmy.info/tree/master/springboot-start-project/springboot-start-app" \
      org.opencontainers.image.base.name="setmyinfo/setmy-info-rocky-java:latest"

COPY target/${APP_NAME}-${VERSION}-exec.jar /opt/setmy.info/lib/microservice.jar
RUN chown root:root /opt/setmy.info/lib/microservice.jar && \
    chmod 0644 /opt/setmy.info/lib/microservice.jar && \
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

EXPOSE 8082/tcp

USER microservice
#CMD java ${JAVA_OPTS} -jar /opt/setmy.info/lib/microservice.jar
CMD [ "smi-spring-boot" ]
