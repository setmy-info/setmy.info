openssl genpkey -algorithm RSA -out priv.key.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in priv.key.pem -out pub.key.pem
openssl pkcs8 -topk8 -inform PEM -outform DER -in priv.key.pem -out priv.key.der -nocrypt
cat priv.key.pem
cat pub.key.pem
