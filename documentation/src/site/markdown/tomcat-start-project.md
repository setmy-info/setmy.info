# tomcat-start-project

## Information

## Installation

### CentOS

    sudo useradd microservice --shell /sbin/nologin --no-create-home
    export ANT_OPTS='-Dhttp.proxyHost=cache.example -Dhttp.proxyPort=8080 -Dhttps.proxyHost=cache.example -Dhttps.proxyPort=8080'
    
    ant download.tomcat
    sudo tar xvzf ./download/apache-tomcat-9.0.13.tar.gz -C /opt
    sudo chown microservice:microservice /opt/apache-tomcat-9.0.13/temp /opt/apache-tomcat-9.0.13/webapps /opt/apache-tomcat-9.0.13/work

## Configuration

https://technet.sector19.net/linux/create-systemd-service-for-tomcat/

## Usage

## See also

    [xxxx](http://yyyyy)
