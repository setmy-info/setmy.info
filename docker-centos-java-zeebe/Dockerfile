FROM setmyinfo/setmy-info-centos-java:13.0.1

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB Centos Java Zeebe" \
      org.label-schema.version="0.20.1" \
      org.label-schema.description="setmy.info Docker HUB Centos Java Zeebe" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

COPY zeebe-broker-0.20.1 /opt/zeebe-broker
RUN chown -R root:root /opt/zeebe-broker
RUN mkdir /opt/zeebe-broker/data
RUN mkdir /opt/zeebe-broker/logs
RUN chown -R microservice:microservice /opt/zeebe-broker/data
RUN chown -R microservice:microservice /opt/zeebe-broker/logs
ENV ZEEBE_HOME /opt/zeebe-broker
ENV PATH="${ZEEBE_HOME}/bin:${PATH}"
RUN cd /opt/zeebe-broker && ls -la
USER microservice
RUN whoami

EXPOSE 26500/tcp
