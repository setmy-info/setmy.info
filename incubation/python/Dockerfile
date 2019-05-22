FROM setmyinfo/setmy-info-centos:v1.0.4

MAINTAINER Imre Tabur "imre.tabur@eesti.ee"

LABEL org.label-schema.name="Docker HUB CentOS Python micro service base" \
      org.label-schema.version="v3.4" \
      org.label-schema.description="setmy.info Docker HUB Centos Python Flask micro service base" \
      org.label-schema.vendor="Hear And See Systems LLC" \
      org.label-schema.url="https://www.hearandseesystems.com" \
      org.label-schema.license="MIT" \
      org.label-schema.schema-version="1.0" \
      org.label-schema.build-date=$BUILD_DATE

ENV http_proxy http://cache.kp:8080
ENV https_proxy http://cache.kp:8080

#RUN yum -y update
RUN yum -y install wget
#RUN yum -y install python33
RUN python --version
RUN wget -c https://bootstrap.pypa.io/get-pip.py
RUN python get-pip.py --proxy=http://cache.kp:8080
RUN pip --version

RUN mkdir -p /opt/has /opt/has/bin /opt/has/lib /var/opt/has /var/opt/has/microservice

RUN useradd microservice --shell /sbin/nologin --no-create-home
RUN chown -R root:root                  /opt/has
RUN chown -R microservice:microservice  /var/opt/has

COPY ./requirements.txt /opt/has/bin/requirements.txt
WORKDIR /opt/has/bin
RUN pip install -r requirements.txt
COPY . /opt/has/bin

EXPOSE 3000

CMD cd /opt/has/bin && ./src/application
#docker build -t flask-tutorial:latest .
#docker run -d -p 5000:5000 flask-tutorial
