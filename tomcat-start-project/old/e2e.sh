#!/bin/sh

function downloadSelenium() {
    if [ ! -f selenium-server-standalone-2.52.0.jar ]; then
        wget -c http://selenium-release.storage.googleapis.com/2.52/selenium-server-standalone-2.52.0.jar
    fi
    return ${?}
}

function installProtractor() {
    #sudo npm install -g protractor
    su -c 'npm install -g protractor'
    return ${?}
}

function runSelenium() {
    xterm -e java -jar selenium-server-standalone-2.52.0.jar &
    return ${?}
}

function runTest() {
    protractor ./src/test/js/e2e/protractor.conf.js
    return 0
}

function runServer() {
    xterm -e mvn jetty:run &
    return 0
}

if [ -n "${1}" ]; then
    #downloadSelenium installProtractor runServer
    ${1}
else
    echo "Command not set!"
fi

exit ${?}
