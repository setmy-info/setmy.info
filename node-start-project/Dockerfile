FROM setmyinfo/setmy-info-centos-node:v10.15.3-1

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Spring boot micro service base" \
      org.label-schema.version="v1.2.0" \
      org.label-schema.description="setmy.info Docker HUB Centos Spring boot micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

#ENV http_proxy http://cache.example.com:8080
#ENV https_proxy http://cache.example.com:8080

RUN mkdir -p /opt/has /opt/has/bin /opt/has/lib /opt/has/src /var/opt/has /var/opt/has/microservice
COPY package*.json /opt/has/src/
COPY . /opt/has/src/
#RUN npm config set proxy http://cache.example.com:8080
#RUN npm config set https-proxy http://cache.example.com:8080
RUN node --version
RUN cd /opt/has/src && npm install

RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN chown -R root:root                  /opt/has
RUN chown -R microservice:microservice  /var/opt/has

EXPOSE 3000

CMD cd /opt/has/src && npm start
