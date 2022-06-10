#!/bin/dash

HELLO_WORLD="Hello World! This is dash!"
echo "1: ${HELLO_WORLD#*!}"
echo "2: ${HELLO_WORLD##*!}"
echo "3: ${HELLO_WORLD%*!}"
echo "4: ${HELLO_WORLD%%*!}"

echo "5: ${HELLO_WORLD#!*}"
echo "6: ${HELLO_WORLD##!*}"
echo "7: ${HELLO_WORLD%!*}"
echo "8: ${HELLO_WORLD%%!*}"

echo "${0}"
echo "9: ${0%%/*}"
echo "10: ${0##*/}"


exit 0