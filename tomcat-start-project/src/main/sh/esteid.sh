#!/bin/sh

PEM_FILE=`pwd`/id.crt
KEY_FILE_CACERTS=${JAVA_HOME}/jre/lib/security/cacerts
KEY_FILE_JSSCACERTS=${JAVA_HOME}/jre/lib/security/jssecacerts
CERT_ALIAS=id.ee
CSR_FILE=`pwd`/server.csr
SERVER_CERT_FILE=`pwd`/server.crt

echo "KEY_FILE_CACERTS: ${KEY_FILE_CACERTS}"
echo "KEY_FILE_JSSCACERTS: ${KEY_FILE_JSSCACERTS}"

#openssl genrsa -out server.key 1024
#openssl req -new -key server.key -out ${CSR_FILE}
#openssl req -in ${CSR_FILE} -noout -text
#openssl x509 -req -in ${CSR_FILE} -signkey server.key -out ${SERVER_CERT_FILE}

#wget -c http://sk.ee/upload/files/JUUR-SK.PEM.cer -O JUUR-SK.PEM.cer
#wget -c http://sk.ee/upload/files/EECCRCA.pem.cer -O EECCRCA.pem.cer
#wget -c http://sk.ee/upload/files/ESTEID-SK%202007.PEM.cer -O ESTEID-SK_2007.PEM.cer
#wget -c http://sk.ee/upload/files/ESTEID-SK%202011.pem.cer -O ESTEID-SK_2011.pem.cer

#cat JUUR-SK.PEM.cer EECCRCA.pem.cer ESTEID-SK_2007.PEM.cer ESTEID-SK_2011.pem.cer > ${PEM_FILE}

#keytool -printcert -file id.crt
#su -c "keytool -import -trustcacerts -file ${PEM_FILE} -alias ${CERT_ALIAS} -keystore ${KEY_FILE_CACERTS}"
#su -c "keytool -import -trustcacerts -file ${PEM_FILE} -alias ${CERT_ALIAS} -keystore ${KEY_FILE_JSSCACERTS}"
#su -c "keytool -import -alias tomcat -keystore ${KEY_FILE_CACERTS} -file ${SERVER_CERT_FILE}"
#su -c "keytool -import -alias tomcat -keystore ${KEY_FILE_JSSCACERTS} -file ${SERVER_CERT_FILE}"

#wget -c http://www.sk.ee/crls/esteid/esteid2007.crl
#wget -c http://www.sk.ee/crls/juur/crl.crl
#wget -c http://www.sk.ee/crls/eeccrca/eeccrca.crl
#wget -c http://www.sk.ee/repository/crls/esteid2011.crl

#openssl crl -in esteid2007.crl -out esteid2007.crl -inform DER
#openssl crl -in crl.crl -out crl.crl -inform DER
#openssl crl -in eeccrca.crl -out eeccrca.crl -inform DER
#openssl crl -in esteid2011.crl -out esteid2011.crl -inform DER

exit 0
