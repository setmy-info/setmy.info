FROM setmyinfo/setmy-info-rocky-java-tomcat:latest

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB Rocky Spring boot micro service base" \
      org.label-schema.version="1.2.0-SNAPSHOT" \
      org.label-schema.description="setmy.info Docker HUB Centos Spring boot micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

#ENV http_proxy http://cache.example.com:8080
#ENV https_proxy http://cache.example.com:8080

COPY target/tomcat-start-project-1.2.0-SNAPSHOT.war /opt/tomcat/webapps/tomcat-start-project-1.2.0-SNAPSHOT.war
