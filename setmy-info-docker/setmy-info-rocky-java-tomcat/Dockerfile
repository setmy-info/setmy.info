FROM setmyinfo/setmy-info-rocky-java:latest

# http://label-schema.org/rc1/ replaced with https://github.com/opencontainers/image-spec/blob/main/annotations.md
LABEL org.opencontainers.image.title="Docker HUB Rocky Tomcat" \
      org.opencontainers.image.description="setmy.info Docker HUB Rocky Tomcat" \
      org.opencontainers.image.version="10.1.26-1" \
      org.opencontainers.image.revision="" \
      org.opencontainers.image.licenses="MIT" \
      org.opencontainers.image.vendor="Hear And See Systems LLC" \
      org.opencontainers.image.created=$BUILD_DATE \
      org.opencontainers.image.documentation="" \
      org.opencontainers.image.authors="Imre Tabur <imre.tabur@mail.ee>" \
      org.opencontainers.image.source="https://github.com/setmy-info/setmy.info/tree/master/setmy-info-docker/setmy-info-rocky-java-tomcat" \
      org.opencontainers.image.url="https://github.com/setmy-info/setmy.info/tree/master/setmy-info-docker/setmy-info-rocky-java-tomcat" \
      org.opencontainers.image.ref.name="" \
      org.opencontainers.image.base.name="setmyinfo/setmy-info-rocky-java:latest"

COPY apache-tomcat-10.1.26 /opt/tomcat

RUN mkdir -p /opt/tomcat/logs && \
    mkdir -p /opt/tomcat/temp && \
    mkdir -p /opt/tomcat/conf/Catalina/ && \
    useradd tomcat --shell /sbin/nologin --no-create-home && \
    chown -R root:root     /opt/tomcat && \
    chown -R tomcat:tomcat /opt/tomcat/work/ && \
    chown -R tomcat:tomcat /opt/tomcat/temp/ && \
    chown -R tomcat:tomcat /opt/tomcat/logs/ && \
    chown -R tomcat:tomcat /opt/tomcat/webapps/ && \
    chown -R tomcat:tomcat /opt/tomcat/conf/Catalina/ && \
    chmod goa+r /opt/tomcat/conf/* && \
    chmod ugo+rx /opt/tomcat/bin/*.sh
# TODO : tomcat std apps deletion

ENV CATALINA_HOME /opt/tomcat
ENV PATH="${CATALINA_HOME}/bin:${PATH}"

WORKDIR /opt/tomcat

USER tomcat
RUN cd /opt/tomcat && ls -la && pwd && whoami

EXPOSE 8080/tcp

CMD ["catalina.sh", "run"]
