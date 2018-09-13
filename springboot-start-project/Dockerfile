FROM setmyinfo/setmy-info-centos-java:v1.0.1

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Spring boot micro service base" \
      org.label-schema.version="v1.0.0" \
      org.label-schema.description="setmy.info Docker HUB Centos Spring boot micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

# docker run -p 8080:8080 setmyinfo/setmy-info-java-microservice:v1.0.0
# docker run -e "SPRING_PROFILES_ACTIVE=dev" setmyinfo/setmy-info-java-microservice:v1.0.0

# TODO : move that into base images with user creation etc
RUN mkdir -p /opt/has /opt/has/bin /opt/has/lib /var/opt/has /var/opt/has/microservice
COPY target/springboot-start-project-1.0.0-SNAPSHOT.jar /opt/has/lib/app.jar
RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN chown -R root:root                  /opt/has
RUN chown -R microservice:microservice  /var/opt/has

WORKDIR /var/opt/has/microservice

EXPOSE 8080/tcp
EXPOSE 8443/tcp

CMD ["/bin/sh"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/has/lib/app.jar"]
